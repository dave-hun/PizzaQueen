package com.example.springbootpizzaqueen.Entities;

import lombok.Data;

@Data
public abstract class Product {
    private String name;

    private Integer price;

    private String description;
}
