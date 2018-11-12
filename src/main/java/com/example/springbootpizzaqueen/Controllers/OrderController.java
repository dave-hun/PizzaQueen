package com.example.springbootpizzaqueen.Controllers;

import com.example.springbootpizzaqueen.Entities.Orders;
import com.example.springbootpizzaqueen.Repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // Repository
    @Autowired
    private OrdersRepository ordersRepository;

    // Methods
    @GetMapping("")
    public Iterable<Orders> getOrders(){
        return ordersRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable Integer id){
        Optional<Orders> order = ordersRepository.findById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order){
        return ResponseEntity.ok(ordersRepository.save(order));
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<Orders> editOrder(@PathVariable Integer id, @RequestBody Orders order){
        Optional<Orders> exist = ordersRepository.findById(id);
        if (exist.isPresent()) {
            Orders modifiable = exist.get();
            // if(user.getUserName() != null) modifiable.setUserName(user.getUserName()); // TODO: rájönni miért nincs getter/setter
            return ResponseEntity.ok(ordersRepository.save(modifiable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteOrder(@PathVariable Integer id){
        Optional<Orders> existorder = ordersRepository.findById(id);
        if (existorder.isPresent()) {
            ordersRepository.delete(existorder.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
