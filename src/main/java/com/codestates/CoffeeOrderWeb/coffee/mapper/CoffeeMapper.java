package com.codestates.CoffeeOrderWeb.coffee.mapper;

import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeePatchDto;
import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeePostDto;
import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeeResponseDto;
import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);

    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);

    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);

    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);
}
