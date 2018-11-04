package com.example.springbootpizzaqueen.Entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderId;

    private Integer userId;

    private List<Product> products;

    private Integer totalAmount;

    private Date orderTime;

    private Date deliveryTime;
}
