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
@Table(name = "users")

public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    String name;
    String email;
    String password;


    public void print() {
            System.out.println("Name : " +name + " ("+email +") " );
    }
}
