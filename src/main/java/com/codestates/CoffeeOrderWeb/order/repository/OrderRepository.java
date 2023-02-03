package com.codestates.CoffeeOrderWeb.order.repository;

import com.codestates.CoffeeOrderWeb.order.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Page<Order> findAll(Pageable pageable);
}
