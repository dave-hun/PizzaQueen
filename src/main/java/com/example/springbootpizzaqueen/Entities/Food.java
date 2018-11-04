package com.example.springbootpizzaqueen.Entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Food extends Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer foodId;

    private boolean spicy;

    private boolean vegetarian;
}
