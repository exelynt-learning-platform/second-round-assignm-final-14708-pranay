package com.ecommerce.app.repository;

import com.ecommerce.app.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


//    @Query("SELECT c FROM Cart c WHERE c.user.email = ?1")
//    Cart findCartByEmail(String email);

    @Query("SELECT c FROM Cart c WHERE c.user.email = :email")
    Cart findCartByEmail(@Param("email") String email);

    @Query("SELECT c FROM Cart c WHERE c.user.email = :email AND c.cartId = :cartId")
    Cart findCartByEmailAndCartId(@Param("email") String email, @Param("cartId") Long cartId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems ci JOIN FETCH ci.product p WHERE p.productId = :productId")
    List<Cart> findCartsByProductId(@Param("productId") Long productId);
}
