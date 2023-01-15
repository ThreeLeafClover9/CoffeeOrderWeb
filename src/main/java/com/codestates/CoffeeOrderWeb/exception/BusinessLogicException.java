package com.codestates.CoffeeOrderWeb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessLogicException extends RuntimeException {
    private ExceptionCode exceptionCode;
}
