package com.codestates.CoffeeOrderWeb.coffee.repository;

import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    Optional<Coffee> findByCoffeeCode(String coffeeCode);

    Page<Coffee> findAll(Pageable pageable);
}
