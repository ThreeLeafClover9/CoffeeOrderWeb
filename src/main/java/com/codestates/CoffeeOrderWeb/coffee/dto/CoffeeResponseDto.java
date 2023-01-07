package com.codestates.CoffeeOrderWeb.coffee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoffeeResponseDto {
    private long coffeeId;
    private String korName;
    private String engName;
    private Integer price;
}
