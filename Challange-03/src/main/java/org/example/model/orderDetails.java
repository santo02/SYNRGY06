package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class orderDetails {
    private Long id;
    private Long order_id;
    private Long food_id;
    private int quantity;
    private double total_Price;
}
