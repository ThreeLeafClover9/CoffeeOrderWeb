package com.codestates.CoffeeOrderWeb.coffee.service;

import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    public Coffee createCoffee(Coffee coffee) {
        Coffee createdCoffee = coffee;
        createdCoffee.setCoffeeId(1L);
        return createdCoffee;
    }

    public Coffee updateCoffee(Coffee coffee) {
        Coffee updatedCoffee = coffee;
        return updatedCoffee;
    }

    public Coffee findCoffee(long coffeeId) {
        Coffee foundCoffee = new Coffee(coffeeId, "아메리카노", "Americano", 2500, "A");
        return foundCoffee;
    }

    public List<Coffee> findCoffees() {
        List<Coffee> foundCoffees = List.of(
                new Coffee(1L, "아메리카노", "Americano", 2500, "A"),
                new Coffee(2L, "카라멜 라뗴", "Caramel Latte", 5000, "CRL")
        );
        return foundCoffees;
    }

    public void deleteCoffee(long coffeeId) {

    }
}
