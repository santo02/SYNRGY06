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
    public orders orderProduct(orders order) {
        order.setOrderTime(LocalDateTime.now());
        order.setCompleted(false);

        OrderDetails orderDetail = new OrderDetails();
        order.addOrderDetail(orderDetail);

        orderDetail.setQuantity(orderDetail.getQuantity());

        Double productPrice = orderDetail.getProducts().getPrice();
        orderDetail.setTotalPrice(orderDetail.getQuantity() * productPrice);

        return orderRepository.save(order);
    }

    public List<orders> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<OrderDetails> getOrderDetailsForUser(UUID userId) {
        return orderDetailRepository.findByUserId(userId);
    }
}
