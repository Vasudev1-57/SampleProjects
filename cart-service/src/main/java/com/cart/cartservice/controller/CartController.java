package com.cart.cartservice.controller;

import com.cart.cartservice.ValueObject.ResponseTemplateVO;
import com.cart.cartservice.dto.CartDto;
import com.cart.cartservice.entity.Cart;
import com.cart.cartservice.repository.CartRepository;
import com.cart.cartservice.service.CartService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/create")
    public ResponseEntity<Object> CreateCart(@RequestBody @Valid CartDto cartDto ){
        log.info("Create Cart Call");
        Cart cart =cartService .CreateCart(cartDto);
        return new ResponseEntity<>(cart , HttpStatus.CREATED );


    }
    @PostMapping("/update")
    public ResponseEntity<Object> UpdateCart(@RequestBody @Valid CartDto cartDto){
        log.info("Update Cart Call");
        Cart cart=cartService .UpdateCart(cartDto);
        return new ResponseEntity<>(cart , HttpStatus.CREATED );

    }
    @GetMapping("/all_carts")
    private ResponseEntity<List<Cart>> GetAllCarts() throws CartNotFoundException {
        log.info("Get All Carts Call");
        List<Cart> cart = cartService .GetAllCarts();
        if(cart.size()== 0){
            throw new CartNotFoundException("Cart Details Not Found " );
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/get_cart_id/{id}")
    public Optional<Cart > GetCartById_id(@PathVariable("id") int id) throws CartNotFoundException {
        log.info("Get Cart By Id Call");
        Optional<Cart> cart = cartService .GetCartById2(id);
        if(cart.isEmpty()){
            throw new CartNotFoundException("Cart Details Not Found " );
        }
        return cart;

    }

    @GetMapping("/get_cart/{id}")
    public ResponseTemplateVO GetCartById(@PathVariable int id,@RequestHeader("Userid") String Userid) throws CartNotFoundException {
        log.info("Delete Cart Call");
        Optional<Cart> cart = cartService .GetCartById1(id,Integer.parseInt(Userid));
        if(cart.isEmpty()){
            throw new CartNotFoundException("Cart Details Not Found " );
        }
        return cartService.GetCartById(id);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>  DeleteCart(@PathVariable("id") int id) {
        cartRepository .deleteById(id);
        return new ResponseEntity<>("Cart Code " + id + "was deleted.", HttpStatus.OK);
    }
}
