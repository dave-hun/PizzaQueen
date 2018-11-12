package com.example.springbootpizzaqueen.Controllers;

import com.example.springbootpizzaqueen.Entities.Drink;
import com.example.springbootpizzaqueen.Entities.Food;
import com.example.springbootpizzaqueen.Repository.DrinkRepository;
import com.example.springbootpizzaqueen.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    // Repositories
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private DrinkRepository drinkRepository;

    // Food
    @GetMapping("/food")
    public Iterable<Food> getAllFood(){
        return foodRepository.findAll();
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFood(@PathVariable Integer id){
        Optional<Food> food = foodRepository.findById(id);
        return food.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/food")
    public ResponseEntity<Food> createFood(@RequestBody Food food){
        return ResponseEntity.ok(foodRepository.save(food));
    }

    @DeleteMapping("/food/{id}")
    public void deleteFood(@PathVariable Integer id){
        foodRepository.deleteById(id);
    }

    // Drink
    @GetMapping("/drink")
    public Iterable<Drink> getAllDrink(){
        return drinkRepository.findAll();
    }

    @GetMapping("/drink/{id}")
    public ResponseEntity<Drink> getDrink(@PathVariable Integer id){
        Optional<Drink> drink = drinkRepository.findById(id);
        return drink.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        /* A felette lévő sor ugyanez a kód, csak fancy-bb csomagolásban:
        if(drink.isPresent()) {
            return ResponseEntity.ok(drink.get());
        }else{
            return ResponseEntity.notFound().build();
        }*/
    }

    @PostMapping("/drink")
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drink){
        return ResponseEntity.ok(drinkRepository.save(drink));
    }

    @DeleteMapping("/drink/{id}")
    public void deleteDrink(@PathVariable Integer id){
        drinkRepository.deleteById(id);
    }

}
