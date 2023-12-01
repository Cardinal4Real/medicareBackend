package com.simplilearn.medicareBackend.controllers;

import com.simplilearn.medicareBackend.entities.Orders;
import com.simplilearn.medicareBackend.services.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@Slf4j
public class OrdersController {
    @Autowired
    OrdersService ordersService;

    @PostMapping("/save_order")
    //@Transactional
    public ResponseEntity<String> addOrders(@RequestBody List<Orders> ordersList) {
        log.info("Saving orders");
        ordersService.addOrders(ordersList);
        return ResponseEntity.ok().body("Products ordered.");
    }

    @GetMapping("/findall_orders")
    public List<Orders> getOrders() {
        return ordersService.getOrders();
    }
}
