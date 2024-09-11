package br.nagualcode.kitchenorders.service;

import br.nagualcode.kitchenorders.domain.KitchenOrder;
import br.nagualcode.kitchenorders.repository.KitchenOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

@SpringBootTest
@Testcontainers
public class KitchenOrderServiceTest {

    @Autowired
    private KitchenOrderService kitchenOrderService;

    @Autowired
    private KitchenOrderRepository kitchenOrderRepository;

    @Container
    public static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test")
            .withUsername("postgres")
            .withPassword("password");

    @DynamicPropertySource
    static void configureTestDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @BeforeEach
    void setUp() {
        kitchenOrderRepository.deleteAll();
    }

    @Test
    void testCreateOrder() {
        KitchenOrder order = new KitchenOrder();
        order.setProduct("Product123");
        order.setQuantity(2);
        order.setTotalPrice(BigDecimal.ZERO);
        order.setCustomerId(1L);

        Mono<KitchenOrder> createdOrder = kitchenOrderService.createOrder(order);

        StepVerifier.create(createdOrder)
                .expectNextMatches(savedOrder -> savedOrder.getId() != null && savedOrder.getProduct().equals("Product123"))
                .verifyComplete();
    }
}
