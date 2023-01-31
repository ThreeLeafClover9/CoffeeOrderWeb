package com.codestates.CoffeeOrderWeb.coffee.controller;

import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeePatchDto;
import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeePostDto;
import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeeResponseDto;
import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import com.codestates.CoffeeOrderWeb.coffee.mapper.CoffeeMapper;
import com.codestates.CoffeeOrderWeb.coffee.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/coffees")
@Validated
@RequiredArgsConstructor
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CoffeeMapper coffeeMapper;

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        Coffee coffee = coffeeMapper.coffeePostDtoToCoffee(coffeePostDto);
        Coffee createdCoffee = coffeeService.createCoffee(coffee);
        URI location = UriComponentsBuilder
                .fromPath("/v1/coffees")
                .path("/{coffee-id}")
                .buildAndExpand(createdCoffee.getCoffeeId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);
        Coffee coffee = coffeeMapper.coffeePatchDtoToCoffee(coffeePatchDto);
        Coffee updatedCoffee = coffeeService.updateCoffee(coffee);
        CoffeeResponseDto coffeeResponseDto = coffeeMapper.coffeeToCoffeeResponseDto(updatedCoffee);
        return new ResponseEntity<>(coffeeResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        Coffee foundCoffee = coffeeService.findCoffee(coffeeId);
        CoffeeResponseDto coffeeResponseDto = coffeeMapper.coffeeToCoffeeResponseDto(foundCoffee);
        return new ResponseEntity<>(coffeeResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees() {
        List<Coffee> foundCoffees = coffeeService.findCoffees();
        List<CoffeeResponseDto> coffeeResponseDtos = coffeeMapper.coffeesToCoffeeResponseDtos(foundCoffees);
        return new ResponseEntity<>(coffeeResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        coffeeService.deleteCoffee(coffeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
