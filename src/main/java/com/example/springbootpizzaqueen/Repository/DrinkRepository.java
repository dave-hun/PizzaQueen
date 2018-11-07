package com.example.springbootpizzaqueen.Repository;

import com.example.springbootpizzaqueen.Entities.Drink;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends CrudRepository<Drink, Integer> {
}
