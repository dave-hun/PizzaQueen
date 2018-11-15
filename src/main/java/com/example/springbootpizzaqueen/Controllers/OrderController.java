package com.example.springbootpizzaqueen.Controllers;

import com.example.springbootpizzaqueen.Entities.Orders;
import com.example.springbootpizzaqueen.Entities.User;
import com.example.springbootpizzaqueen.Repository.OrdersRepository;
import com.example.springbootpizzaqueen.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // Repository
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private UserRepository userRepository;

    // Methods
    @GetMapping("")
    @Secured({"ROLE_ADMIN"})
    public Iterable<Orders> getOrders(){
        return ordersRepository.findAll();
    }

    @GetMapping("/my")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public Iterable<Orders> getMyOrders(Principal principal){
        List<Orders> myOrders = new ArrayList<>();
        if(userRepository.findByUserName(principal.getName()).isPresent()) {
            for (Orders o:ordersRepository.findAll()) {
                if(principal.getName().equals(o.getO_user().getUserName())) myOrders.add(o);
            }
        }
        return myOrders;
    }

    // TODO: NULLPOINTEREXCEPTION az order.getO_user.getUserName()-nél, mert a táblák még érdekesen vannak összecsatolva
    @GetMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<Orders> getOrder(@PathVariable Integer id, Principal principal){
        Optional<Orders> order = ordersRepository.findById(id);
        if(order.isPresent()){
            if(userRepository.findByUserName(principal.getName()).isPresent()) {
                // Ha a kérdező admin
                if (userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                    return ResponseEntity.ok(order.get());
                }
                // Ha a saját rendelését kérdezi
                if (userRepository.findByUserName(principal.getName()).get().getUserName()
                        .equals(order.get().getO_user().getUserName())) { // NULLPOINTEREXCEPTION!
                    return ResponseEntity.ok(order.get());
                }else{ // Ha másvalaki megrendelését próbálja lekérdezni
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
        }
        // Ha nem létezik
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order, Principal principal){
        if (userRepository.findByUserName(principal.getName()).isPresent()) {
            // Ha a posztoló admin
            if (userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                return ResponseEntity.ok(ordersRepository.save(order));
            }
            // Ha saját magának vesz fel rendelést
            if (userRepository.findByUserName(principal.getName()).get().getUserName().equals(order.getO_user().getUserName())) { // TODO
                return ResponseEntity.ok(ordersRepository.save(order));
            }else { // Ha másvalakinek próbál meg rendelni
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }
        return ResponseEntity.badRequest().build(); // Nem létezik a rendelő
    }


    @PatchMapping("/orders/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<Orders> editOrder(@PathVariable Integer id, @RequestBody Orders order, Principal principal){
        Optional<Orders> exist = ordersRepository.findById(id);
        if (exist.isPresent()) {
            if (userRepository.findByUserName(principal.getName()).isPresent()) {
                Orders modifiable = exist.get();
                // Ha a módosító admin
                if (userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                    if (order.getO_user() != null) modifiable.setO_user(order.getO_user());
                }
                // Ha a saját rendelését módosítja
                if (principal.getName().equals(modifiable.getO_user().getUserName())) {
                    if (order.getOrderTime() != null) modifiable.setOrderTime(order.getOrderTime());
                    if (order.getFoods() != null) modifiable.setFoods(order.getFoods());
                    if (order.getDrinks() != null) modifiable.setDrinks(order.getDrinks());
                    if (order.getTotalAmount() != null) modifiable.setTotalAmount(order.getTotalAmount());
                    if (order.getDeliveryTime() != null) modifiable.setDeliveryTime(order.getDeliveryTime());
                }else { // Ha másvalaki megrendelését próbálja módosítani
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
                return ResponseEntity.ok(ordersRepository.save(modifiable));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity deleteOrder(@PathVariable Integer id, Principal principal) {
        Optional<Orders> existorder = ordersRepository.findById(id);
        if (existorder.isPresent()) {
            if (userRepository.findByUserName(principal.getName()).isPresent()) {
                // Ha a kérdező admin
                if (userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                    ordersRepository.delete(existorder.get());
                    return ResponseEntity.ok().build();
                }
                // Ha a saját rendelését kérdezi
                if (principal.getName().equals(existorder.get().getO_user().getUserName())) {
                    ordersRepository.delete(existorder.get());
                    return ResponseEntity.ok().build();
                } else { // Ha másvalaki megrendelését próbálja lekérdezni
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
}
