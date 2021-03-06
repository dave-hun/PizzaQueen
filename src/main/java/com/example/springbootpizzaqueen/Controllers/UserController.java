package com.example.springbootpizzaqueen.Controllers;

import com.example.springbootpizzaqueen.Entities.User;
import com.example.springbootpizzaqueen.Repository.UserRepository;
import com.example.springbootpizzaqueen.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    // Repository
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticatedUser authenticatedUser;

    // Methods
    @GetMapping("")
    @Secured({"ROLE_ADMIN"})
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<User> getUser(@PathVariable Integer id, Principal principal) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            if(userRepository.findByUserName(principal.getName()).isPresent()) {
                // Ha a kérdező admin
                if (userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                    return ResponseEntity.ok(user.get());
                }
                // Ha saját magát kérdezi
                if (principal.getName().equals(user.get().getUserName())) {
                    return ResponseEntity.ok(user.get());
                }else{ // Ha nem admin és mást próbál lekérdezni
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
        }
        // Ha nem létezik
        return ResponseEntity.notFound().build();
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUserName(user.getUserName());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //User.Role requestRole = user.getRole();
        user.setRole(User.Role.ROLE_USER);
        /*if(principal != null){
            Optional<User> p_user = userRepository.findByUserName(principal.getName());
            // Ha a létrehozó admin, akkor hozhat létre admint ő is
            if (p_user.isPresent()) {
                if (userRepository.findByUserName(principal.getName()).isPresent()) {
                    if (userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                        user.setRole(requestRole);
                    }
                }
            }
        }*/
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping("/createAdmin")
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity<User> createAdmin(@RequestBody User user){
        Optional<User> oUser = userRepository.findByUserName(user.getUserName());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PatchMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity<User> editUser(@PathVariable Integer id, @RequestBody User user, Principal principal) {
        Optional<User> exist = userRepository.findById(id);
        if (exist.isPresent()) {
            User modifiable = exist.get();
            if(userRepository.findByUserName(principal.getName()).isPresent()) {
                // Ha a saját adatait akarja módosítani, vagy admin módosít
                if (principal.getName().equals(modifiable.getUserName())
                        || userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                    if (user.getRealName() != null) modifiable.setRealName(user.getRealName());
                    if (user.getPassword() != null) modifiable.setPassword(passwordEncoder.encode(user.getPassword()));
                    if (user.getAddress() != null) modifiable.setAddress(user.getAddress());
                    if (user.getPhoneNumber() != null) modifiable.setPhoneNumber(user.getPhoneNumber());
                    // Ha a módosító admin, akkor ezeket is teheti
                    if (userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                        //if (user.getUserId() != null) modifiable.setUserId(user.getUserId());
                        if (user.getUserName() != null) modifiable.setUserName(user.getUserName());
                        if (user.getRole() != null) modifiable.setRole(user.getRole());
                        if (user.getOrders() != null) modifiable.setOrders(user.getOrders());
                    }
                    return ResponseEntity.ok(userRepository.save(modifiable));
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    public ResponseEntity deleteUser(@PathVariable Integer id, Principal principal) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            if(userRepository.findByUserName(principal.getName()).isPresent()) {
                // Ha a törlő admin
                if (userRepository.findByUserName(principal.getName()).get().getRole().equals(User.Role.ROLE_ADMIN)) {
                    userRepository.delete(user.get());
                    return ResponseEntity.ok().build();
                }
                // Ha saját magát szeretné törölni
                if (principal.getName().equals(user.get().getUserName())) {
                    userRepository.delete(user.get());
                    return ResponseEntity.ok().build();
                }else{ // Ha nem admin és mást próbál törölni
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                }
            }
        }
        // Ha nem létezik
        return ResponseEntity.notFound().build();


    }

    @PostMapping("login")
    public ResponseEntity<User> login() {
        return ResponseEntity.ok(authenticatedUser.getUser());
    }
}
