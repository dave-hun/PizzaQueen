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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer drinkId;

    private String name;

    private Integer price;

    private String description;

    @ManyToMany(mappedBy = "drinks")
    List<Orders> orders;
}
