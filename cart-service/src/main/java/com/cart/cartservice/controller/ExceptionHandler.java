package com.cart.cartservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBussinessException(CartNotFoundException bussinessException) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("message", bussinessException.getMessage() );
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
