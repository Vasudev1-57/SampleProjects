package com.cart.cartservice.ValueObject;

import com.cart.cartservice.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateVO {
    private Cart cart;
    private User user;
    private Product product;
}
