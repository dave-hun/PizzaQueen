package com.example.springbootpizzaqueen.Repository;

import com.example.springbootpizzaqueen.Entities.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
