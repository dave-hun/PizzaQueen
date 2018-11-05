package com.example.springbootpizzaqueen.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderId;

    //@ManyToOne
    private Integer userId;

    @ManyToMany
    private List<Food> foods;

    @ManyToMany
    private List<Drink> drinks;

    private Integer totalAmount;

    private Date orderTime;

    private Date deliveryTime;
}
