package com.examen.pedidos.service.impl;

import com.examen.pedidos.dto.OrderRequestDto;
import com.examen.pedidos.entity.Order;
import com.examen.pedidos.entity.Product;
import com.examen.pedidos.repositories.CustomerRepository;
import com.examen.pedidos.repositories.OrderRepository;
import com.examen.pedidos.repositories.ProductRepository;
import com.examen.pedidos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;


    @Transactional
    public Mono<Order> saveOrder(OrderRequestDto orderRequest) {
        // Validar que el cliente exista
        return customerRepository.findById(orderRequest.getCustomerId())
                .switchIfEmpty(Mono.error(new RuntimeException("Cliente no encontrado")))
                .flatMap(customer -> {
                    return productRepository.findAllById(
                                    List.of(orderRequest.getProductId())
                            )
                            .collectList()
                            .flatMap(products -> {
                                if (products.isEmpty()) {
                                    return Mono.error(new RuntimeException("Producto no encontrado"));
                                }
                                // Calcular el precio total
                                double totalPrice = products.stream()
                                        .mapToDouble(Product::getPrecio)
                                        .sum();

                                // Configurar los datos del pedido
                                Order order = new Order();
                                order.setCustomer(customer);
                                order.setProduct(products.get(0));
                                order.setTotalPrice(totalPrice);
                                order.setQuantity(orderRequest.getQuantity());

                                // Guardar el pedido
                                return orderRepository.save(order);
                            });
                });
    }

    @Transactional(readOnly = true)
    public Mono<Order> getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")));
    }

    @Transactional
    public Mono<Order> updateOrder(String orderId, OrderRequestDto orderRequest) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
                .flatMap(existingOrder -> {
                    return customerRepository.findById(orderRequest.getCustomerId())
                            .switchIfEmpty(Mono.error(new RuntimeException("Cliente no encontrado")))
                            .flatMap(customer -> productRepository.findAllById(
                                            List.of(orderRequest.getProductId())
                                    )
                                    .collectList()
                                    .flatMap(products -> {
                                        if (products.isEmpty()) {
                                            return Mono.error(new RuntimeException("Producto no encontrado"));
                                        }
                                        // Actualizar los datos del pedido
                                        double totalPrice = products.stream()
                                                .mapToDouble(Product::getPrecio)
                                                .sum();
                                        existingOrder.setCustomer(customer);
                                        existingOrder.setProduct(products.get(0));
                                        existingOrder.setTotalPrice(totalPrice);
                                        existingOrder.setQuantity(orderRequest.getQuantity());
                                        return orderRepository.save(existingOrder);
                                    }));
                });
    }

    @Transactional
    public Mono<Void> deleteOrder(String orderId) {
        return orderRepository.findById(orderId)
                .switchIfEmpty(Mono.error(new RuntimeException("Pedido no encontrado")))
                .flatMap(order -> orderRepository.delete(order));
    }

    @Transactional(readOnly = true)
    public Flux<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}