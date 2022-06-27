package com.cleanhub.assignment.customerservice.persistence;

import com.cleanhub.assignment.customerservice.persistence.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    @Override
    List<CustomerEntity> findAll();
}
