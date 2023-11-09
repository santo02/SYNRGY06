package com.challenge4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "products")
public class products {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    String name;
    Double price;

    @ManyToOne(targetEntity = merchants.class)
    @JoinColumn(name = "merchant_id")
    private merchants merchant;


    public void print() {
        System.out.println("Name : " +name +"\t\t | Harga " +price + "\t\t ( " +merchant.getName() + " )");
    }
}
