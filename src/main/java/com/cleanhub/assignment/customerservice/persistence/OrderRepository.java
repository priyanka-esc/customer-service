package com.cleanhub.assignment.customerservice.persistence;

import com.cleanhub.assignment.customerservice.persistence.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
    List<OrderEntity> findAll() ;
}
