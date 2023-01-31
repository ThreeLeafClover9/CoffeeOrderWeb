package com.codestates.CoffeeOrderWeb.order.controller;

import com.codestates.CoffeeOrderWeb.coffee.service.CoffeeService;
import com.codestates.CoffeeOrderWeb.order.dto.OrderPostDto;
import com.codestates.CoffeeOrderWeb.order.dto.OrderResponseDto;
import com.codestates.CoffeeOrderWeb.order.entity.Order;
import com.codestates.CoffeeOrderWeb.order.mapper.OrderMapper;
import com.codestates.CoffeeOrderWeb.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@Validated
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CoffeeService coffeeService;

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderMapper.orderPostDtoToOrder(orderPostDto);
        Order createdOrder = orderService.createOrder(order);
        URI location = UriComponentsBuilder
                .fromPath("/v1/orders")
                .path("/{order-id}")
                .buildAndExpand(createdOrder.getOrderId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order foundOrder = orderService.findOrder(orderId);
        OrderResponseDto orderResponseDto = orderMapper.orderToOrderResponseDto(coffeeService, foundOrder);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> foundOrders = orderService.findOrders();
        List<OrderResponseDto> orderResponseDtos = orderMapper.ordersToOrderResponseDtos(coffeeService, foundOrders);
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
