package com.codestates.CoffeeOrderWeb.order.service;

import com.codestates.CoffeeOrderWeb.coffee.service.CoffeeService;
import com.codestates.CoffeeOrderWeb.exception.BusinessLogicException;
import com.codestates.CoffeeOrderWeb.exception.ExceptionCode;
import com.codestates.CoffeeOrderWeb.member.service.MemberService;
import com.codestates.CoffeeOrderWeb.order.entity.Order;
import com.codestates.CoffeeOrderWeb.order.mapper.OrderMapper;
import com.codestates.CoffeeOrderWeb.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final CoffeeService coffeeService;
    private final OrderMapper orderMapper;

    public Order createOrder(Order order) {
        memberService.findVerifiedMember(order.getMemberId().getId());
        order.getOrderCoffees()
                .stream()
                .forEach(coffeeRef -> coffeeService.findVerifiedCoffee(coffeeRef.getCoffeeId()));
        return orderRepository.save(order);
    }

    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public void cancelOrder(long orderId) {
        Order foundOrder = findVerifiedOrder(orderId);
        int stepNumber = foundOrder.getOrderStatus().getStepNumber();
        if (stepNumber >= 2) {
            throw new BusinessLogicException(ExceptionCode.ORDER_NOT_MODIFIED);
        }
        foundOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
        orderRepository.save(foundOrder);
    }

    private Order findVerifiedOrder(long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
    }
}
