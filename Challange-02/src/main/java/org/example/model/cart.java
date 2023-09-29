package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class cart {
    private food foodItem;
    private int quantity;
    public cart(food foodItem, int quantity){
        this.foodItem= foodItem;
        this.quantity = quantity;
    }
}
