package com.codestates.CoffeeOrderWeb.order.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table("ORDER_COFFEE")
public class CoffeeRef {
    @Id
    private long orderCoffeeId;
    private long coffeeId;
    private int quantity;
}
