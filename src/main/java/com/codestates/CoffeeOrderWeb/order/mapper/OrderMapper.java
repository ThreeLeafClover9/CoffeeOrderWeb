package com.codestates.CoffeeOrderWeb.order.mapper;

import com.codestates.CoffeeOrderWeb.order.dto.OrderPostDto;
import com.codestates.CoffeeOrderWeb.order.dto.OrderResponseDto;
import com.codestates.CoffeeOrderWeb.order.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderPostDtoToOrder(OrderPostDto orderPostDto);

    OrderResponseDto orderToOrderResponseDto(Order order);

    List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders);
}
