package com.challenge4.controller;

import com.challenge4.model.OrderDetails;
import com.challenge4.repository.OrderDetailRepository;
import com.challenge4.repository.OrderRepository;
import com.challenge4.service.MerchantService;
import com.challenge4.service.OrderDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;
    private final static Logger logger =  LoggerFactory.getLogger(OrderDetailController.class);

    @GetMapping("/{orderDetailsId}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable UUID orderDetailsId) {
        Optional<OrderDetails> orderDetails = orderDetailService.getOrderDetailsById(orderDetailsId);
        return orderDetails.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
