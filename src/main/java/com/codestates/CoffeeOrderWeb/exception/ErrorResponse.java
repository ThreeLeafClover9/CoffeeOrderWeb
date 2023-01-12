package com.codestates.CoffeeOrderWeb.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    private List<FieldError> fieldErrors;
    private List<ConstraintViolationError> constraintViolationErrors;

    public static ErrorResponse of(BindingResult bindingResult) {
        return new ErrorResponse(FieldError.of(bindingResult), null);
    }

    public static ErrorResponse of(Set<ConstraintViolation<?>> constraintViolations) {
        return new ErrorResponse(null, ConstraintViolationError.of(constraintViolations));
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FieldError {
        private String field;
        private Object value;
        private String message;

        public static List<FieldError> of(BindingResult bindingResult) {
            List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<FieldError> errors = new ArrayList<>();
            for (org.springframework.validation.FieldError fieldError : fieldErrors) {
                FieldError error = new FieldError(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
                errors.add(error);
            }
            return errors;
        }
    }

    @Getter
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ConstraintViolationError {
        private String propertyPath;
        private Object value;
        private String message;

        public static List<ConstraintViolationError> of(Set<ConstraintViolation<?>> constraintViolations) {
            List<ConstraintViolationError> errors = new ArrayList<>();
            for (ConstraintViolation<?> constraintViolation : constraintViolations) {
                ConstraintViolationError error = new ConstraintViolationError(constraintViolation.getPropertyPath().toString(), constraintViolation.getInvalidValue(), constraintViolation.getMessage());
                errors.add(error);
            }
            return errors;
        }
    }
}
