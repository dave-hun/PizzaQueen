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

    @PatchMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Integer id, @RequestBody User user){
        Optional<User> existuser = userRepository.findById(id);
        if(existuser.isPresent()){
            //order.setId(existuser.get().getId()); // TODO: rájönni hogy @Data, @Entity páros esetén miért nincsenek getterek/setterek
            return ResponseEntity.ok(userRepository.save(user));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userRepository.deleteById(id);
    }
}
