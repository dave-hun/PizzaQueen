package com.example.springbootpizzaqueen.Entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Food extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer foodId;

    private String name;

    private Integer price;

    private String description;

    private boolean spicy;

    private boolean vegetarian;

    @ManyToMany(mappedBy = "foods")
    List<Orders> orders;
}
