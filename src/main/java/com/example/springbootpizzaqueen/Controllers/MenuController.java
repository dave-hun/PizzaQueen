package com.example.springbootpizzaqueen.Controllers;

import com.example.springbootpizzaqueen.Entities.Drink;
import com.example.springbootpizzaqueen.Entities.Food;
import com.example.springbootpizzaqueen.Repository.DrinkRepository;
import com.example.springbootpizzaqueen.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    public Iterable<Food> getAllFood() {
        return foodRepository.findAll();
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFood(@PathVariable Integer id) {
        Optional<Food> food = foodRepository.findById(id);
        return food.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/food")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        return ResponseEntity.ok(foodRepository.save(food));
    }

    @PatchMapping("/food/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Food> editFood(@PathVariable Integer id, @RequestBody Food food){
        Optional<Food> exist = foodRepository.findById(id);
        if (exist.isPresent()) {
            Food modified = exist.get();
            if(modified.getName() != null) modified.setName(food.getName());
            if(modified.getDescription() != null) modified.setDescription(food.getDescription());
            if(modified.getPrice() != null) modified.setPrice(food.getPrice());
            if(modified.isSpicy() != food.isSpicy()) modified.setSpicy(food.isSpicy());
            if(modified.isVegetarian() != food.isVegetarian()) modified.setVegetarian(food.isVegetarian());
            return ResponseEntity.ok(foodRepository.save(modified));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/food/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity deleteFood(@PathVariable Integer id) {
        Optional<Food> exist = foodRepository.findById(id);
        if (exist.isPresent()) {
            foodRepository.delete(exist.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Drink
    @GetMapping("/drink")
    public Iterable<Drink> getAllDrink() {
        return drinkRepository.findAll();
    }

    @GetMapping("/drink/{id}")
    public ResponseEntity<Drink> getDrink(@PathVariable Integer id) {
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
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Drink> createDrink(@RequestBody Drink drink) {
        return ResponseEntity.ok(drinkRepository.save(drink));
    }

    @PatchMapping("/drink/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<Drink> editDrink(@PathVariable Integer id, @RequestBody Drink drink){
        Optional<Drink> exist = drinkRepository.findById(id);
        if (exist.isPresent()) {
            Drink modified = exist.get();
            if(modified.getName() != null) modified.setName(drink.getName());
            if(modified.getDescription() != null)modified.setDescription(drink.getDescription());
            if(modified.getPrice() != null)modified.setPrice(drink.getPrice());
            return ResponseEntity.ok(drinkRepository.save(modified));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/drink/{id}")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity deleteDrink(@PathVariable Integer id) {
        Optional<Drink> exist = drinkRepository.findById(id);
        if (exist.isPresent()) {
            drinkRepository.delete(exist.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
