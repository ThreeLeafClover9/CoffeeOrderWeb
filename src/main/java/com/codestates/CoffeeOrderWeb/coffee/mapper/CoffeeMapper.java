package com.codestates.CoffeeOrderWeb.coffee.mapper;

import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeePatchDto;
import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeePostDto;
import com.codestates.CoffeeOrderWeb.coffee.dto.CoffeeResponseDto;
import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);

    @Mapping(target = "price", qualifiedByName = "unwrap")
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);

    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);

    List<CoffeeResponseDto> coffeesToCoffeeResponseDtos(List<Coffee> coffees);

    @Named("unwrap")
    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }
}
