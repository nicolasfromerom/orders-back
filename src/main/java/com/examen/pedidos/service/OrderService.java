package com.examen.pedidos.service;

import com.examen.pedidos.dto.OrderRequestDto;
import com.examen.pedidos.entity.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<Order> saveOrder(OrderRequestDto orderRequest);
    Mono<Order> getOrderById(String orderId);
    Mono<Order> updateOrder(String orderId, OrderRequestDto orderRequest);
    Mono<Void> deleteOrder(String orderId);

    Flux<Order> getAllOrders();


}
