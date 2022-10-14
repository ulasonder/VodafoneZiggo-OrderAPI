package com.vodafone.orderapi.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.vodafone.orderapi.data.Order;
import com.vodafone.orderapi.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Long> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(CREATED).body(orderService.createOrder(order));
    }
}
