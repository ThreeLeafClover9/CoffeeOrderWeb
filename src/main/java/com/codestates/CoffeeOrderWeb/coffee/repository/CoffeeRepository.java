package com.codestates.CoffeeOrderWeb.coffee.repository;

import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    @Override
    List<Coffee> findAll();
}
