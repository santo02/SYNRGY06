package com.challenge4.repository;

import com.challenge4.model.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetails, UUID> {
    @Query("SELECT od FROM OrderDetails od " +
            "JOIN FETCH od.orders o " +
            "WHERE o.users.id = :userId")
    List<OrderDetails> findByUserId(@Param("userId") UUID userId);

}
