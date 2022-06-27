package com.cleanhub.assignment.customerservice.persistence;


import com.cleanhub.assignment.customerservice.persistence.model.OrderEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest
public class CustomOrderRepoTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomOrderRepository customOrderRepository;

    @Test
    public void saveTest()
    {
        orderRepository.save(OrderEntity.builder()
                .name("test_order")
                .uuid("test_uuid")
                .build());
        List<OrderEntity> orders = orderRepository.findAll();
        assertTrue(orders.size()>0);
    }

    @Test
    public void topCustomers()
    {
        List<String> list = customOrderRepository.getTopCustomers(5,"10 days");
        assertTrue(list.size()>0);
    }

}
