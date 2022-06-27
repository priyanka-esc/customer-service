package com.cleanhub.assignment.customerservice.manager;

import com.cleanhub.assignment.customerservice.clients.CleanHubClient;
import com.cleanhub.assignment.customerservice.dto.Customer;
import com.cleanhub.assignment.customerservice.persistence.CustomerRepository;
import com.cleanhub.assignment.customerservice.persistence.model.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomerManager {

    private final CustomerRepository customerRepository;
    private final CleanHubClient cleanHubClient;

    CustomerManager(CustomerRepository customerRepository, CleanHubClient cleanHubClient) {
        this.customerRepository = customerRepository;
        this.cleanHubClient = cleanHubClient;
    }

    public void addCustomers() {
        List<Customer> customers = cleanHubClient.getCustomers();
        if (!customers.isEmpty()) {
            List<CustomerEntity> customerEntityList = new ArrayList<>();
            customers.forEach(customer -> customerEntityList.add(CustomerEntity.builder()
                    .uuid(customer.getUuid())
                    .name(customer.getName())
                    .build()));
            customerRepository.saveAll(customerEntityList);
        }
    }

}
