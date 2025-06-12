package com.examen.pedidos.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id; // MongoDB usa String para IDs por defecto
    private Customer customer; // Información completa del cliente
    private Product product;   // Información completa del producto
    private int quantity;
    private double totalPrice;
}