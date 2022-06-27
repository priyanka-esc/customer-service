package com.cleanhub.assignment.customerservice.controller;

import com.cleanhub.assignment.customerservice.manager.OrderManager;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc
public class CustomerControllerTest {

    String CUSTOMER_API = "/customers/top-customers-by-order-growth";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderManager orderManager;

    @Before("")
    public void setup()
    {
        List<String> topCustomers = new ArrayList<>();
        topCustomers.add("test_customer1");
        topCustomers.add("test_customer2");
        when(orderManager.getCustomersWithMaxIncreaseInQuantity(any(),any())).thenReturn(java.util.Optional.of(topCustomers));
    }

    @Test
    public void getTopCustomers() throws Exception {
        mockMvc.perform(get(CUSTOMER_API))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
        verify(orderManager, times(1)).getCustomersWithMaxIncreaseInQuantity(any(), any());
        verifyNoMoreInteractions(orderManager);
    }
}
