package com.codestates.CoffeeOrderWeb.order.service;

import com.codestates.CoffeeOrderWeb.coffee.service.CoffeeService;
import com.codestates.CoffeeOrderWeb.exception.BusinessLogicException;
import com.codestates.CoffeeOrderWeb.exception.ExceptionCode;
import com.codestates.CoffeeOrderWeb.member.service.MemberService;
import com.codestates.CoffeeOrderWeb.order.entity.Order;
import com.codestates.CoffeeOrderWeb.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final CoffeeService coffeeService;

    public Order createOrder(Order order) {
        Long memberId = order.getMemberId().getId();
        memberService.findMember(memberId);
        order.getOrderCoffees()
                .stream()
                .forEach(coffeeRef -> coffeeService.findCoffee(coffeeRef.getCoffeeId()));
        return orderRepository.save(order);
    }

    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }

    public Page<Order> findOrders(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("orderId").descending());
        return orderRepository.findAll(pageRequest);
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
