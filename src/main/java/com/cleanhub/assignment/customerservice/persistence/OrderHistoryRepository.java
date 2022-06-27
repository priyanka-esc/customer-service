package com.cleanhub.assignment.customerservice.persistence;

import com.cleanhub.assignment.customerservice.persistence.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Integer> {
}
