package com.cleanhub.assignment.customerservice.persistence;

import java.util.List;

public interface CustomOrderRepository  {
    List<String> getTopCustomers(int limit, String interval);
}
