package com.codestates.CoffeeOrderWeb.order.mapper;

import com.codestates.CoffeeOrderWeb.coffee.entity.Coffee;
import com.codestates.CoffeeOrderWeb.coffee.service.CoffeeService;
import com.codestates.CoffeeOrderWeb.order.dto.OrderCoffeePostDto;
import com.codestates.CoffeeOrderWeb.order.dto.OrderCoffeeResponseDto;
import com.codestates.CoffeeOrderWeb.order.dto.OrderPostDto;
import com.codestates.CoffeeOrderWeb.order.dto.OrderResponseDto;
import com.codestates.CoffeeOrderWeb.order.entity.CoffeeRef;
import com.codestates.CoffeeOrderWeb.order.entity.Order;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order orderPostDtoToOrder(OrderPostDto orderPostDto);

    default AggregateReference memberIdToAggregateReference(long memberId) {
        return AggregateReference.to(memberId);
    }

    CoffeeRef orderCoffeeDtoToCoffeeRef(OrderCoffeePostDto orderCoffeeDto);

    OrderResponseDto orderToOrderResponseDto(@Context CoffeeService coffeeService, Order order);

    default long AggregateReferenceToMemberId(AggregateReference aggregateReference) {
        return (long) aggregateReference.getId();
    }

    default OrderCoffeeResponseDto coffeeRefToOrderCoffeeResponseDto(@Context CoffeeService coffeeService, CoffeeRef coffeeRef) {
        Coffee coffee = coffeeService.findCoffee(coffeeRef.getCoffeeId());
        OrderCoffeeResponseDto orderCoffeeResponseDto = new OrderCoffeeResponseDto();
        orderCoffeeResponseDto.setCoffeeId( coffeeRef.getCoffeeId() );
        orderCoffeeResponseDto.setKorName( coffee.getKorName() );
        orderCoffeeResponseDto.setEngName( coffee.getEngName() );
        orderCoffeeResponseDto.setPrice( coffee.getPrice() );
        orderCoffeeResponseDto.setQuantity( coffeeRef.getQuantity() );
        return orderCoffeeResponseDto;
    }

    List<OrderResponseDto> ordersToOrderResponseDtos(@Context CoffeeService coffeeService, List<Order> orders);
}
