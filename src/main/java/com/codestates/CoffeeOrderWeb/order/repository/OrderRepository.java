package com.codestates.CoffeeOrderWeb.order.repository;

import com.codestates.CoffeeOrderWeb.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    @Override
    List<Order> findAll();
}
