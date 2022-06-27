package com.cleanhub.assignment.customerservice.manager;

import com.cleanhub.assignment.customerservice.clients.CleanHubClient;
import com.cleanhub.assignment.customerservice.dto.Order;
import com.cleanhub.assignment.customerservice.persistence.CustomOrderRepository;
import com.cleanhub.assignment.customerservice.persistence.CustomerRepository;
import com.cleanhub.assignment.customerservice.persistence.OrderHistoryRepository;
import com.cleanhub.assignment.customerservice.persistence.OrderRepository;
import com.cleanhub.assignment.customerservice.persistence.model.CustomerEntity;
import com.cleanhub.assignment.customerservice.persistence.model.OrderEntity;
import com.cleanhub.assignment.customerservice.persistence.model.OrderHistory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderManager {

    private static final String DEFAULT_UNIT_OF_TIME = "days";
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final CleanHubClient cleanHubClient;
    private CustomOrderRepository customOrderRepository;

    OrderManager(CustomerRepository customerRepository, CleanHubClient cleanHubClient, OrderRepository orderRepository,
                 CustomOrderRepository customOrderRepository, OrderHistoryRepository orderHistoryRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.cleanHubClient = cleanHubClient;
        this.customOrderRepository = customOrderRepository;
        this.orderHistoryRepository = orderHistoryRepository;
    }


    public void getOrders() {
        List<CustomerEntity> customers = customerRepository.findAll();
        getOrderForEachCustomerAndSave(customers);
    }

    private void getOrderForEachCustomerAndSave(List<CustomerEntity> customers) {
        for (CustomerEntity customer : customers) {
            Order order = cleanHubClient.getOrderByCustomer(customer.getName());
            //better to do it with a batch update at once
            OrderEntity orderEntity = OrderEntity.builder()
                    .uuid(order.getUuid())
                    .name(order.getName())
                    .build();
            //transactional
            orderRepository.save(orderEntity);
            orderHistoryRepository.save(OrderHistory.builder()
                    .orderEntity(orderEntity)
                    .lastModified(order.getLastModified())
                    .quantity(order.getQuantity())
                    .quantityUnit(order.getQuantityUnit())
                    .build());
        }
    }

    public Optional<List<String>> getCustomersWithMaxIncreaseInQuantity(int limit, int intervalCount) {
        String interval = String.valueOf(intervalCount).concat(DEFAULT_UNIT_OF_TIME);
        return Optional.of(customOrderRepository.getTopCustomers(limit,interval));
    }

}
