package com.test.belong.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class BelongControllerAdvice {
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleCustomerNotFound(CustomerNotFoundException exception) {
        log.error("BELONG-SERVICE-ERROR", exception);
        String bodyOfResponse = exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(bodyOfResponse);
    }

    @ExceptionHandler(InvalidPhoneNumberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleInvalidPhoneNumber(InvalidPhoneNumberException exception) {
        log.error("BELONG-SERVICE-ERROR", exception);
        String bodyOfResponse = exception.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bodyOfResponse);
    }
}
