package com.codestates.CoffeeOrderWeb.coffee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1/coffees")
public class CoffeeController {
    private final Map<Long, Map<String, Object>> coffees = new HashMap<>();
    long coffeeId = 1L;

    @PostConstruct
    public void init() {
        Map<String, Object> coffee = new HashMap<>();
        coffee.put("coffeeId", coffeeId);
        coffee.put("korName", "바닐라 라떼");
        coffee.put("engName", "Vanilla Latte");
        coffee.put("price", 4500);
        coffees.put(coffeeId, coffee);
    }

    @PostMapping
    public ResponseEntity postCoffee(@RequestParam("korName") String korName,
                                     @RequestParam("engName") String engName,
                                     @RequestParam("price") int price) {
        Map<String, Object> coffee = new HashMap<>();
        coffee.put("coffeeId", ++coffeeId);
        coffee.put("korName", korName);
        coffee.put("engName", engName);
        coffee.put("price", price);
        coffees.put(coffeeId, coffee);
        return new ResponseEntity<>(coffee, HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") long coffeeId,
                                      @RequestParam("korName") String korName,
                                      @RequestParam("price") String price) {
        Map<String, Object> coffee = coffees.get(coffeeId);
        coffee.put("korName", korName);
        coffee.put("price", price);
        coffees.put(coffeeId, coffee);
        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") long coffeeId) {
        System.out.println("coffeeId = " + coffeeId);

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        System.out.println("get Coffees");

        // not implementation
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") long coffeeId) {
        coffees.remove(coffeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
