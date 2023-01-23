package com.codestates.CoffeeOrderWeb.order.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ORDER_COFFEE")
public class CoffeeRef {
    @Id
    private long orderCoffeeId;
    private long coffeeId;
    private int quantity;
}
