package com.challenge4.service;

import com.challenge4.model.OrderDetails;
import com.challenge4.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    public Optional<OrderDetails> getOrderDetailsById(UUID orderDetailsId) {
        return orderDetailRepository.findById(orderDetailsId);
    }
}
