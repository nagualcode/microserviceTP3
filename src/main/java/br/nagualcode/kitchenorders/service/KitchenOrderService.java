package br.nagualcode.kitchenorders.service;

import br.nagualcode.kitchenorders.domain.KitchenOrder;
import br.nagualcode.kitchenorders.repository.KitchenOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Service
public class KitchenOrderService {

    private final KitchenOrderRepository kitchenOrderRepository;
    private final WebClient.Builder webClientBuilder;

    public KitchenOrderService(KitchenOrderRepository kitchenOrderRepository, WebClient.Builder webClientBuilder) {
        this.kitchenOrderRepository = kitchenOrderRepository;
        this.webClientBuilder = webClientBuilder;
    }

    public Mono<KitchenOrder> createOrder(KitchenOrder order) {
        return fetchProductPrice(order.getProduct())
            .flatMap(price -> {
                order.setTotalPrice(price.multiply(BigDecimal.valueOf(order.getQuantity())));
                return Mono.fromSupplier(() -> kitchenOrderRepository.save(order));
            });
    }

    public Mono<KitchenOrder> getOrderById(Long id) {
        return Mono.fromSupplier(() -> kitchenOrderRepository.findById(id).orElse(null));
    }

    public Mono<Void> deleteOrder(Long id) {
        return Mono.fromRunnable(() -> kitchenOrderRepository.deleteById(id));
    }

    private Mono<BigDecimal> fetchProductPrice(String productId) {
        return webClientBuilder.build()
            .get()
            .uri("https://api.externa.com/product/" + productId)
            .retrieve()
            .bodyToMono(BigDecimal.class)
            .onErrorReturn(BigDecimal.ZERO); // Retorna zero em caso de erro
    }
}
