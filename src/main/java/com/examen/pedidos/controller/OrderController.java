package com.examen.pedidos.controller;

import com.examen.pedidos.dto.OrderRequestDto;
import com.examen.pedidos.entity.Order;
import com.examen.pedidos.service.OrderService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> createOrder(@Valid @RequestBody OrderRequestDto orderRequest) {
            return orderService.saveOrder(orderRequest);
    }

    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Order> getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }

    @PutMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Order> updateOrder(@PathVariable String orderId, @Valid @RequestBody OrderRequestDto orderRequest) {
        return orderService.updateOrder(orderId, orderRequest);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteOrder(@PathVariable String orderId) {
        return orderService.deleteOrder(orderId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}