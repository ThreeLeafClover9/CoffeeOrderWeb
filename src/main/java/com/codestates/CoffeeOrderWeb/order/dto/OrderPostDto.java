package com.codestates.CoffeeOrderWeb.order.dto;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class OrderPostDto {
    @Positive
    private long memberId;
    @Positive
    private long coffeeId;
}
