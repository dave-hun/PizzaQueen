package com.example.springbootpizzaqueen.Repository;

import com.example.springbootpizzaqueen.Entities.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends CrudRepository<Orders, Integer> {
}
