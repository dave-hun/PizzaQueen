package com.example.springbootpizzaqueen.Controllers;

import com.example.springbootpizzaqueen.Entities.Drink;
import com.example.springbootpizzaqueen.Entities.Food;
import com.example.springbootpizzaqueen.Entities.Product;
import com.example.springbootpizzaqueen.Repository.DrinkRepository;
import com.example.springbootpizzaqueen.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Optional;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private DrinkRepository drinkRepository;

    // TODO: ételek ÉS italok visszaadása a GetMapping("")-ben
    /*@GetMapping("")
    public Iterable<Product> getAll(){
        Iterable<Product> products = (Iterable<Product>) foodRepository.findAll();
        products += (Iterable<Product>) drinkRepository.findAll();
        return products;
    }*/

    @GetMapping("/food")
    public Iterable<Food> getAllFood(){
        return foodRepository.findAll();
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> getFood(@PathVariable Integer id){
        Optional<Food> food = foodRepository.findById(id);
        if(food.isPresent()){
            return ResponseEntity.ok(food.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/drink")
    public Iterable<Drink> getAllDrink(){
        return drinkRepository.findAll();
    }

    @GetMapping("/drink/{id}")
    public ResponseEntity<Drink> getDrink(@PathVariable Integer id){
        Optional<Drink> drink = drinkRepository.findById(id);
        if(drink.isPresent()) {
            return ResponseEntity.ok(drink.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
