package com.challenge4.service;

import com.challenge4.model.OrderDetails;
import com.challenge4.model.orders;
import com.challenge4.model.users;
import com.challenge4.repository.MerchantRepository;
import com.challenge4.repository.OrderDetailRepository;
import com.challenge4.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    public orders orderProduct(users user, String destinationAddress, List<OrderDetails> orderDetails) {
        orders newOrder = new orders();
        newOrder.setUsers(user);
        newOrder.setOrderTime(LocalDateTime.now());
        newOrder.setCompleted(false);
        newOrder.setDestinationAddress(destinationAddress);
        orderRepository.save(newOrder);
        for (OrderDetails detail : orderDetails) {
            detail.setOrders(newOrder);
            orderDetailRepository.save(detail);
        }
        return newOrder;
    }

    public List<OrderDetails> getOrderDetailsForUser(UUID userId) {
        return orderDetailRepository.findByUserId(userId);
    }
}
