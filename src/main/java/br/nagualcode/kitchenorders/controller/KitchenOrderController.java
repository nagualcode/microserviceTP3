package br.nagualcode.kitchenorders.controller;

import br.nagualcode.kitchenorders.domain.KitchenOrder;
import br.nagualcode.kitchenorders.service.KitchenOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class KitchenOrderController {

    private final KitchenOrderService kitchenOrderService;

    public KitchenOrderController(KitchenOrderService kitchenOrderService) {
        this.kitchenOrderService = kitchenOrderService;
    }

    @PostMapping
    public Mono<ResponseEntity<KitchenOrder>> createOrder(@RequestBody KitchenOrder order) {
        return kitchenOrderService.createOrder(order)
            .map(savedOrder -> new ResponseEntity<>(savedOrder, HttpStatus.CREATED));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<KitchenOrder>> getOrderById(@PathVariable Long id) {
        return kitchenOrderService.getOrderById(id)
            .map(order -> new ResponseEntity<>(order, HttpStatus.OK))
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteOrder(@PathVariable Long id) {
        return kitchenOrderService.deleteOrder(id)
            .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
    }
}
