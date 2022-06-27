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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "order_history")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "oh_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "oh_order_id")
    OrderEntity orderEntity;

    @Column(name = "oh_last_modified")
    Date lastModified;

    @Column(name="oh_quantity")
    Float quantity;

    @Column(name="oh_quantity_unit")
    String quantityUnit;
}

