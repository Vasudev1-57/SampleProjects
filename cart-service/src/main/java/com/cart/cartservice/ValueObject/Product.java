package com.cart.cartservice.ValueObject;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String productname;
    private String category;
    private float price;
    private float discount;
    private int availableqty;

    private String  deliverytype;
    private float deliverycharge;
    private int replacementdays;
    private Boolean isReplaced;
    private LocalDateTime addeddate;
}
