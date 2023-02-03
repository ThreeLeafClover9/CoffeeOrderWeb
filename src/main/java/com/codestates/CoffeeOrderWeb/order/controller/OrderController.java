package com.codestates.CoffeeOrderWeb.order.controller;

import com.codestates.CoffeeOrderWeb.coffee.service.CoffeeService;
import com.codestates.CoffeeOrderWeb.order.dto.OrderPostDto;
import com.codestates.CoffeeOrderWeb.order.dto.OrderResponseDto;
import com.codestates.CoffeeOrderWeb.order.entity.Order;
import com.codestates.CoffeeOrderWeb.order.mapper.OrderMapper;
import com.codestates.CoffeeOrderWeb.order.service.OrderService;
import com.codestates.CoffeeOrderWeb.response.MultiResponseDto;
import com.codestates.CoffeeOrderWeb.response.SingleResponseDto;
import com.codestates.CoffeeOrderWeb.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
@Validated
@RequiredArgsConstructor
public class OrderController {
    private final static String ORDER_DEFAULT_PATH = "/v1/orders";
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final CoffeeService coffeeService;

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        Order order = orderMapper.orderPostDtoToOrder(orderPostDto);
        Order createdOrder = orderService.createOrder(order);
        URI location = UriCreator.createUri(ORDER_DEFAULT_PATH, createdOrder.getOrderId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Order foundOrder = orderService.findOrder(orderId);
        OrderResponseDto orderResponseDto = orderMapper.orderToOrderResponseDto(coffeeService, foundOrder);
        SingleResponseDto<OrderResponseDto> singleResponseDto = new SingleResponseDto<>(orderResponseDto);
        return new ResponseEntity<>(singleResponseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(@RequestParam @Positive int page,
                                    @RequestParam @Positive int size) {
        Page<Order> foundOrderPage = orderService.findOrders(page, size);
        List<Order> foundOrders = foundOrderPage.getContent();
        List<OrderResponseDto> orderResponseDtos = orderMapper.ordersToOrderResponseDtos(coffeeService, foundOrders);
        MultiResponseDto<OrderResponseDto> multiResponseDto = new MultiResponseDto<>(orderResponseDtos, foundOrderPage);
        return new ResponseEntity<>(multiResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity cancelOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
