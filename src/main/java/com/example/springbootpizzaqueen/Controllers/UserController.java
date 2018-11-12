package com.example.springbootpizzaqueen.Controllers;

import com.example.springbootpizzaqueen.Entities.User;
import com.example.springbootpizzaqueen.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    // Repository
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Methods
    @GetMapping("")
    //@Secured({"ROLE_USER"})
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_USER"})
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUserName(user.getUserName());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_USER);
        return ResponseEntity.ok(userRepository.save(user));
    }
    /*// TODO ?: login
    @PostMapping("login")
    public ResponseEntity login(@RequestBody User user) {
       return ResponseEntity.ok().build();
    }*/

    @PatchMapping("/{id}")
    public ResponseEntity<User> editUser(@PathVariable Integer id, @RequestBody User user) {
        Optional<User> exist = userRepository.findById(id);
        if (exist.isPresent()) {
            User modifiable = exist.get();
            if(user.getUserName() != null) modifiable.setUserName(user.getUserName()); // ez biztosan módosítható legyen?
            if(user.getRole() != null) modifiable.setRole(user.getRole()); // ez biztosan módosítható legyen?
            if(user.getPassword() != null) modifiable.setPassword(passwordEncoder.encode(user.getPassword()));
            if(user.getRealName() != null) modifiable.setRealName(user.getRealName());
            if(user.getAddress() != null) modifiable.setAddress(user.getAddress());
            if(user.getPhoneNumber() != null) modifiable.setPhoneNumber(user.getPhoneNumber());
            if(user.getOrders() != null) modifiable.setOrders(user.getOrders()); // nem inkább += legyen?
            return ResponseEntity.ok(userRepository.save(modifiable));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Optional<User> exist = userRepository.findById(id);
        if (exist.isPresent()) {
            userRepository.delete(exist.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
