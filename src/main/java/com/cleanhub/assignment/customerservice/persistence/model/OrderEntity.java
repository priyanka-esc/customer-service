package com.cleanhub.assignment.customerservice.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "o_id")
        private Long id;

        @Column(name = "o_uuid")
        private String uuid;

        @Column(name = "o_name")
        private String name;

        @OneToOne
        @JoinColumn(name = "o_customer_id")
        CustomerEntity customerEntity;

}
