package com.codestates.CoffeeOrderWeb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found"),
    MEMBER_EXISTS(409, "Member Exists"),
    COFFEE_NOT_FOUND(404, "Coffee Not Found"),
    COFFEE_EXISTS(409, "Coffee Exists"),
    ORDER_NOT_MODIFIED(403, "Order Not Modified"),
    ORDER_NOT_FOUND(404, "Order Not Found");

    private int status;
    private String message;
}
