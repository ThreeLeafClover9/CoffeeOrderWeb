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
    @PostMapping
    public ResponseEntity postCoffee(@RequestParam("korName") String korName,
                                     @RequestParam("engName") String engName,
                                     @RequestParam("price") int price) {
        Map<String, Object> coffee = new HashMap<>();
        coffee.put("korName", korName);
        coffee.put("engName", engName);
        coffee.put("price", price);
        return new ResponseEntity<>(coffee, HttpStatus.CREATED);
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") long coffeeId,
                                      @RequestParam("korName") String korName,
                                      @RequestParam("price") String price) {
        Map<String, Object> coffee = new HashMap<>();
        coffee.put("coffeeId", coffeeId);
        coffee.put("korName", korName);
        coffee.put("engName", "Vanilla Latte");
        coffee.put("price", price);
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
        // No need business logic
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
