package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class order {
    private food foodItem;
    private int quantity;
    private String varian;
    public order(food foodItem, int quantity, String varian){
        this.foodItem= foodItem;
        this.quantity = quantity;
        this.varian = varian;
    }
}
