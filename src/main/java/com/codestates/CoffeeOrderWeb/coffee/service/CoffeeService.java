package com.codestates.CoffeeOrderWeb.coffee.service;

import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import com.codestates.CoffeeOrderWeb.coffee.repository.CoffeeRepository;
import com.codestates.CoffeeOrderWeb.exception.BusinessLogicException;
import com.codestates.CoffeeOrderWeb.exception.ExceptionCode;
import com.codestates.CoffeeOrderWeb.order.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoffeeService {
    private final CoffeeRepository coffeeRepository;

    public Coffee createCoffee(Coffee coffee) {
        String coffeeCode = coffee.getCoffeeCode().toUpperCase();
        verifyExistsCoffeeCode(coffeeCode);
        coffee.setCoffeeCode(coffeeCode);
        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(Coffee coffee) {
        Coffee foundCoffee = findVerifiedCoffee(coffee.getCoffeeId());
        Optional.ofNullable(coffee.getKorName()).ifPresent(foundCoffee::setKorName);
        Optional.ofNullable(coffee.getEngName()).ifPresent(foundCoffee::setEngName);
        Optional.ofNullable(coffee.getPrice()).ifPresent(foundCoffee::setPrice);
        return coffeeRepository.save(foundCoffee);
    }

    public Coffee findCoffee(long coffeeId) {
        return findVerifiedCoffee(coffeeId);
    }

    public List<Coffee> findOrderedCoffee(Order order) {
        return order.getOrderCoffees()
                .stream()
                .map(coffeeRef -> findVerifiedCoffee(coffeeRef.getCoffeeId()))
                .collect(Collectors.toList());
    }

    public List<Coffee> findCoffees() {
        return coffeeRepository.findAll();
    }

    public void deleteCoffee(long coffeeId) {
        Coffee foundCoffee = findVerifiedCoffee(coffeeId);
        coffeeRepository.delete(foundCoffee);
    }

    private void verifyExistsCoffeeCode(String coffeeCode) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findByCoffeeCode(coffeeCode);
        if (optionalCoffee.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.COFFEE_EXISTS);
        }
    }

    public Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        return optionalCoffee.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));
    }
}
