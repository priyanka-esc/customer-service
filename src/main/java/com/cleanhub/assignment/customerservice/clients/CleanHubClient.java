package com.cleanhub.assignment.customerservice.clients;

import com.cleanhub.assignment.customerservice.dto.Customer;
import com.cleanhub.assignment.customerservice.dto.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "CleanhubApiClient", url = "https://marketplace.cleanhub.com/api/public/")
public interface CleanHubClient {

    @GetMapping(value = "orders/logos")
    List<Customer> getCustomers();

    @GetMapping("/orders")
    Order getOrderByCustomer(@RequestParam(value = "route") String route);

}
