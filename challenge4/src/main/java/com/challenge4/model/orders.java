package com.challenge4.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "orders")
public class orders {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    LocalDateTime orderTime;
    String destinationAddress;

    @ManyToOne(targetEntity = users.class)
    @JoinColumn(name = "user_id")
    private users users;

    boolean isCompleted;


}
