package com.codestates.CoffeeOrderWeb.advice;

import com.codestates.CoffeeOrderWeb.exception.BusinessLogicException;
import com.codestates.CoffeeOrderWeb.exception.ExceptionCode;
import com.codestates.CoffeeOrderWeb.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        return ErrorResponse.of(bindingResult);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        return ErrorResponse.of(constraintViolations);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String message = e.getMessage();
        return ErrorResponse.of(HttpStatus.METHOD_NOT_ALLOWED, message);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String message = e.getMessage();
        return ErrorResponse.of(HttpStatus.BAD_REQUEST, message);
    }

    @ExceptionHandler
    public ResponseEntity handleBusinessLogicException(BusinessLogicException e) {
        ExceptionCode exceptionCode = e.getExceptionCode();
        ErrorResponse errorResponse = ErrorResponse.of(exceptionCode);
        int status = exceptionCode.getStatus();
        HttpStatus httpStatus = HttpStatus.valueOf(status);
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error("handle exception = ", e);
        return ErrorResponse.of(internalServerError);
    }
}
