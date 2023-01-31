package com.codestates.CoffeeOrderWeb.order.dto;

import com.codestates.CoffeeOrderWeb.order.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private long orderId;
    private long memberId;
    private List<OrderCoffeeResponseDto> orderCoffees;
    private Order.OrderStatus orderStatus;
    private LocalDateTime createdAt;
}
