package com.example.springbootpizzaqueen.Controllers;

import com.example.springbootpizzaqueen.Entities.User;
import com.example.springbootpizzaqueen.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    // Repository
    @Autowired
    private UserRepository userRepository;

    // Methods
    @GetMapping("")
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.ok(userRepository.save(user));
    }
}
