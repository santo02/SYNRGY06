package com.challenge4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

    @Setter
    @Getter
    @NoArgsConstructor
    @Entity
    @Table(name = "orderDetail")
    public class OrderDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @ManyToOne(targetEntity = products.class)
        @JoinColumn(name = "product_id")
        private products products;

        @ManyToOne(targetEntity = orders.class)
        @JoinColumn(name = "order_id")
        private orders orders;

        int quantity;
        Double totalPrice;
    }
