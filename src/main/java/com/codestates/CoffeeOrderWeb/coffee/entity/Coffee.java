package com.codestates.CoffeeOrderWeb.coffee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table
public class Coffee {
    @Id
    private long coffeeId;
    private String korName;
    private String engName;
    private Integer price;
    private String coffeeCode;
}
