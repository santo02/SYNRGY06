package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class cart {
    private food foodItem;
    private int quantity;
    private String varian;
    public cart(food foodItem, int quantity, String varian){
        this.foodItem= foodItem;
        this.quantity = quantity;
        this.varian = varian;
    }
}
