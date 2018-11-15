package com.example.springbootpizzaqueen.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "orders")
    //@JsonIgnore
    private User o_user;

    @ManyToMany
    //@JsonIgnore
    private List<Food> foods;

    @ManyToMany
    //@JsonIgnore
    private List<Drink> drinks;

    private Integer totalAmount;

    private Date orderTime;

    private Date deliveryTime;
}
