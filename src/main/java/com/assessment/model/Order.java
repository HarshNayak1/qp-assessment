package com.assessment.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private double totalAmount;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
