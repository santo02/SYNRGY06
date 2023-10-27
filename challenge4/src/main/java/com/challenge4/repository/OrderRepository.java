package com.challenge4.repository;

import com.challenge4.model.merchants;
import com.challenge4.model.orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<orders, UUID> {
}
