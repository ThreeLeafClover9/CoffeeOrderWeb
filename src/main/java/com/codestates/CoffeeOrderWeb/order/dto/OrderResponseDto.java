package com.codestates.CoffeeOrderWeb.order.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponseDto {
    private long orderId;
    private long memberId;
    private long coffeeId;
}
