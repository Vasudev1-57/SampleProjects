package com.cart.cartservice.repository;

import com.cart.cartservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    @Query("select c from Cart c where c.id = ?1 and c.userid = ?2")
    Optional<Cart> findByIdAndUserid(int id, int userid);

}
