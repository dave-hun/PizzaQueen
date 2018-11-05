package com.example.springbootpizzaqueen.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Drink extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer drinkId;

    @ManyToMany(mappedBy = "drinks")
    List<Order> order;
}
