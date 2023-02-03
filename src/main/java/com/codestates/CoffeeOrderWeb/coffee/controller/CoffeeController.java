package com.codestates.CoffeeOrderWeb.coffee.controller;

import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeePatchDto;
import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeePostDto;
import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeeResponseDto;
import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import com.codestates.CoffeeOrderWeb.coffee.mapper.CoffeeMapper;
import com.codestates.CoffeeOrderWeb.coffee.service.CoffeeService;
import com.codestates.CoffeeOrderWeb.response.MultiResponseDto;
import com.codestates.CoffeeOrderWeb.response.SingleResponseDto;
import com.codestates.CoffeeOrderWeb.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/coffees")
@Validated
@RequiredArgsConstructor
public class CoffeeController {
    private final static String COFFEE_DEFAULT_PATH = "/v1/coffees";
    private final CoffeeService coffeeService;
    private final CoffeeMapper coffeeMapper;

    @PostMapping
    public ResponseEntity postCoffee(@Valid @RequestBody CoffeePostDto coffeePostDto) {
        Coffee coffee = coffeeMapper.coffeePostDtoToCoffee(coffeePostDto);
        Coffee createdCoffee = coffeeService.createCoffee(coffee);
        URI location = UriCreator.createUri(COFFEE_DEFAULT_PATH, createdCoffee.getCoffeeId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{coffee-id}")
    public ResponseEntity patchCoffee(@PathVariable("coffee-id") @Positive long coffeeId,
                                      @Valid @RequestBody CoffeePatchDto coffeePatchDto) {
        coffeePatchDto.setCoffeeId(coffeeId);
        Coffee coffee = coffeeMapper.coffeePatchDtoToCoffee(coffeePatchDto);
        Coffee updatedCoffee = coffeeService.updateCoffee(coffee);
        CoffeeResponseDto coffeeResponseDto = coffeeMapper.coffeeToCoffeeResponseDto(updatedCoffee);
        SingleResponseDto<CoffeeResponseDto> singleResponseDto = new SingleResponseDto<>(coffeeResponseDto);
        return new ResponseEntity<>(singleResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{coffee-id}")
    public ResponseEntity getCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        Coffee foundCoffee = coffeeService.findCoffee(coffeeId);
        CoffeeResponseDto coffeeResponseDto = coffeeMapper.coffeeToCoffeeResponseDto(foundCoffee);
        SingleResponseDto<CoffeeResponseDto> singleResponseDto = new SingleResponseDto<>(coffeeResponseDto);
        return new ResponseEntity<>(singleResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCoffees(@RequestParam @Positive int page,
                                     @RequestParam @Positive int size) {
        Page<Coffee> foundCoffeePage = coffeeService.findCoffees(page, size);
        List<Coffee> foundCoffees = foundCoffeePage.getContent();
        List<CoffeeResponseDto> coffeeResponseDtos = coffeeMapper.coffeesToCoffeeResponseDtos(foundCoffees);
        MultiResponseDto<CoffeeResponseDto> multiResponseDto = new MultiResponseDto<>(coffeeResponseDtos, foundCoffeePage);
        return new ResponseEntity<>(multiResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{coffee-id}")
    public ResponseEntity deleteCoffee(@PathVariable("coffee-id") @Positive long coffeeId) {
        coffeeService.deleteCoffee(coffeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
