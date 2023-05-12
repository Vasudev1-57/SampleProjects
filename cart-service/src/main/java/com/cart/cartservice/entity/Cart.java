package com.cart.cartservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;

    @Column(nullable = false)
    private LocalDateTime addeddate;
    @Column(nullable = true)
    private LocalDateTime removeddate;
    private Boolean isordered;
    @Column(nullable = false)
    private int userid;
    @Column(nullable = false)
    private int productid;
    @Column(nullable = false)
    private int qty;
    @Column(nullable = false)
    private float price;
    private float discount;
    private float deliverycharge;
    @Column(nullable = false)
    private float totalamount;

}
