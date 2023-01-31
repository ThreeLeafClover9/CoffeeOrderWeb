package com.codestates.CoffeeOrderWeb.order.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class OrderCoffeePostDto {
    @Positive
    private long coffeeId;
    @Positive
    private int quantity;
}
