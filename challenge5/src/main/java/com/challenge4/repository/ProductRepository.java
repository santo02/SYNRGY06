package com.challenge4.repository;

import com.challenge4.model.products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<products, UUID> {
}
