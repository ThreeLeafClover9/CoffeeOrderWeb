package com.codestates.CoffeeOrderWeb.order.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class OrderPostDto {
    @Positive
    private long memberId;
    @Valid
    private List<OrderCoffeePostDto> orderCoffees;
}
