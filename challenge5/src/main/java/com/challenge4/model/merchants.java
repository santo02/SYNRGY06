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
@Table(name = "merchant")
public class merchants {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    String name;
    String location;
    boolean isOpen;

    public void print() {
            System.out.println("Name : "+name +"\t | location :"+location + "\t | Buka: "+ openLabel());
    }
    private String openLabel() {
        return isOpen? "Ya" : "Tidak";
    }
}
