package com.example.springbootpizzaqueen.Repository;

import com.example.springbootpizzaqueen.Entities.Food;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends CrudRepository<Food, Integer> {
}
