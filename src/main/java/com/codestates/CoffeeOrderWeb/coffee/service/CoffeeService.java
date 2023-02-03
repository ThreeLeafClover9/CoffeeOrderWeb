package com.codestates.CoffeeOrderWeb.coffee.service;

import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import com.codestates.CoffeeOrderWeb.coffee.repository.CoffeeRepository;
import com.codestates.CoffeeOrderWeb.exception.BusinessLogicException;
import com.codestates.CoffeeOrderWeb.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        long coffeeId = coffee.getCoffeeId();
        Coffee foundCoffee = findVerifiedCoffee(coffeeId);
        Optional.ofNullable(coffee.getKorName()).ifPresent(foundCoffee::setKorName);
        Optional.ofNullable(coffee.getEngName()).ifPresent(foundCoffee::setEngName);
        Optional.ofNullable(coffee.getPrice()).ifPresent(foundCoffee::setPrice);
        return coffeeRepository.save(foundCoffee);
    }

    public Coffee findCoffee(long coffeeId) {
        return findVerifiedCoffee(coffeeId);
    }

    public Page<Coffee> findCoffees(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("coffeeId").descending());
        return coffeeRepository.findAll(pageRequest);
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

    private Coffee findVerifiedCoffee(long coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        return optionalCoffee.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COFFEE_NOT_FOUND));
    }
}
