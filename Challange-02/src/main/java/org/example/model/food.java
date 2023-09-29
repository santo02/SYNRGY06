package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class food {

    private String name;
    private Double price;

    public food(String name, double price) {
        this.name = name;
        this.price = price;
    }

}
