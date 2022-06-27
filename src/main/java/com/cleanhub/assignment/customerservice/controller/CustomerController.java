package com.cleanhub.assignment.customerservice.controller;


import com.cleanhub.assignment.customerservice.manager.OrderManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class CustomerController {

    @Autowired
    OrderManager orderManager;

    @GetMapping("customers/top-customers-by-order-growth")
    public ResponseEntity<Object> getTopCustomersByOrderGrowth(
            @RequestParam(value = "limit", required = false, defaultValue = "1") final int limit,
            @RequestParam(value = "interval", required = false, defaultValue = "7") final int interval) {
        List<String> customers = orderManager.getCustomersWithMaxIncreaseInQuantity(limit, interval)
                .orElse(Collections.emptyList());

        return new ResponseEntity<>(customers, OK);
    }
}
