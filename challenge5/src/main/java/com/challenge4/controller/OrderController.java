package com.challenge4.controller;

import com.challenge4.model.orders;
import com.challenge4.response.ResponseHandler;
import com.challenge4.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    private final static Logger logger =  LoggerFactory.getLogger(MerchantController.class);
    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody orders order) {
        orders createdOrder = orderService.orderProduct(order);
        return ResponseHandler.generateResponse("Berhasil melakukan order", HttpStatus.OK, createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<orders>> getAll(){
        List<orders> orderList = orderService.getAllOrders();
        return ResponseEntity.ok(orderList);
    }


}
