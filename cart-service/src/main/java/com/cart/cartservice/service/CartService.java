package com.cart.cartservice.service;

import com.cart.cartservice.ValueObject.Product;
import com.cart.cartservice.ValueObject.ResponseTemplateVO;
import com.cart.cartservice.ValueObject.User;
import com.cart.cartservice.controller.CartNotFoundException;
import com.cart.cartservice.dto.CartDto;
import com.cart.cartservice.entity.Cart;
import com.cart.cartservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private RestTemplate restTemplate;

    public Cart CreateCart(CartDto cartDto) {
        float amount = cartDto.getQty() * cartDto.getPrice();
        float discamnt = cartDto.getDiscount() * cartDto.getQty();
        float ttlamount = amount - discamnt;

        Cart cart = Cart.builder()

                .isordered(false)
                .addeddate(LocalDateTime.now())
                .userid(cartDto.getUserid())
                .productid(cartDto.getProductid())
                .qty(cartDto.getQty())
                .price(cartDto.getPrice())
                .discount(cartDto.getDiscount())
                .deliverycharge(cartDto.getDeliverycharge())
                .totalamount(ttlamount)
                .build();
        return cartRepository.save(cart);
    }

    public Cart UpdateCart(CartDto cartDto) {
        float amount = cartDto.getQty() * cartDto.getPrice();
        float discamnt = cartDto.getDiscount() * cartDto.getQty();
        float ttlamount = amount - discamnt;

        Optional<Cart> cart1 = cartRepository.findById(cartDto.getId());
        Cart cart = cart1.get();
        cart = Cart.builder()
                .isordered(false)
                .addeddate(LocalDateTime.now())
                .userid(cartDto.getUserid())
                .productid(cartDto.getProductid())
                .qty(cartDto.getQty())
                .price(cartDto.getPrice())
                .discount(cartDto.getDiscount())
                .deliverycharge(cartDto.getDeliverycharge())
                .totalamount(ttlamount)
                .build();
        return cartRepository.save(cart);
    }

    public List<Cart> GetAllCarts() {
        return cartRepository.findAll();
    }

    public ResponseTemplateVO GetCartById(int id) throws CartNotFoundException {
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        try {

        Optional<Cart> cart1 = cartRepository.findById(id);
        Cart cart = cart1.get();
        User user = restTemplate.getForObject("http://USER-SERVICE/user/get_user/" + cart.getUserid(), User.class);
        Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/product/get_product/" + cart.getProductid(), Product.class);
        responseTemplateVO.setCart(cart);
        responseTemplateVO.setUser(user);
        responseTemplateVO.setProduct(product);
        }
        catch (Exception e){
            throw new CartNotFoundException(e.getMessage());
        }
        return responseTemplateVO;
    }

    public Optional<Cart> GetCartById1(int id,int Userid) {
      //  return cartRepository.findById(id);
        return cartRepository.findByIdAndUserid(id,Userid);

    }

    public Optional<Cart> GetCartById2(int id) {
        return cartRepository.findById(id);
    }
}
