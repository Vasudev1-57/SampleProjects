package com.cart.cartservice.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class CartDto {
    private int id;
    private LocalDateTime addeddate;
    private LocalDateTime removeddate;
    private Boolean isordered;
    private int userid;
    private int productid;
    private int qty;
    private float price;
    private float discount;
    private float deliverycharge;
    private float totalamount;
}
