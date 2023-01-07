package com.codestates.CoffeeOrderWeb.order.controller;

import com.codestates.CoffeeOrderWeb.order.dto.OrderPostDto;
import com.codestates.CoffeeOrderWeb.order.dto.OrderResponseDto;
import com.codestates.CoffeeOrderWeb.order.entity.Order;
import com.codestates.CoffeeOrderWeb.order.mapper.OrderMapper;
import com.codestates.CoffeeOrderWeb.order.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@Validated
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderMapper.orderPostDtoToOrder(orderPostDto);
        Order response = orderService.createOrder(order);
        OrderResponseDto orderResponseDto = orderMapper.orderToOrderResponseDto(order);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order response = orderService.findOrder(orderId);
        OrderResponseDto orderResponseDto = orderMapper.orderToOrderResponseDto(response);
        return new ResponseEntity<>(orderResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Order> response = orderService.findOrders();
        List<OrderResponseDto> orderResponseDtos = orderMapper.ordersToOrderResponseDtos(response);
        return new ResponseEntity<>(orderResponseDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
