package com.codestates.CoffeeOrderWeb.order.service;

import com.codestates.CoffeeOrderWeb.order.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    public Order createOrder(Order order) {
        Order createdOrder = order;
        order.setOrderId(1L);
        return createdOrder;
    }

    public Order findOrder(long orderId) {
        Order foundOrder = new Order(orderId, 1L, 1L);
        return foundOrder;
    }


    public List<Order> findOrders() {
        List<Order> foundOrders = List.of(
                new Order(1L, 1L, 1L),
                new Order(2L, 2L, 2L)
        );
        return foundOrders;
    }

    public void deleteOrder(long orderId) {

    }
}
