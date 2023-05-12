package com.cart.cartservice.controller;

public class CartNotFoundException extends Exception {
    public CartNotFoundException(String Message){
        super(Message );
    }
}
