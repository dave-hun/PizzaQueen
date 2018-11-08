package com.example.springbootpizzaqueen.Repository;

import com.example.springbootpizzaqueen.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    //Optional<User> findByUserName(String username);
}
