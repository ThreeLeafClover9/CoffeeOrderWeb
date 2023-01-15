package com.codestates.CoffeeOrderWeb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "Member Not Found");

    private int status;
    private String message;
}
