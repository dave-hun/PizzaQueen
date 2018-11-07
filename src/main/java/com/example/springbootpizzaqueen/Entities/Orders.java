package com.example.springbootpizzaqueen.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "orders")
    private User o_user;

    @ManyToMany
    private List<Food> foods;

    @ManyToMany
    private List<Drink> drinks;

   private Integer totalAmount;

    private Date orderTime;

    private Date deliveryTime;
}
