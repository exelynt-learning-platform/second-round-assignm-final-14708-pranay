package com.ecommerce.app.controller;

import com.ecommerce.app.dto.request.CartDTO;
import com.ecommerce.app.model.Cart;
import com.ecommerce.app.repository.CartRepository;
import com.ecommerce.app.service.CartService;
import com.ecommerce.app.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/products/{productId}/quantity/{quantity}")
    public ResponseEntity<CartDTO> addProductToCart(@PathVariable(value = "productId") Long productId,
                                                    @PathVariable(value = "quantity") Integer quantity) {
        CartDTO cartDTO = cartService.addProductToCart(productId, quantity);

        return new ResponseEntity<>(cartDTO, HttpStatus.CREATED);
    }

    @GetMapping("/users/cart")
    public ResponseEntity<CartDTO> getCartByUserId() {
        String email = authUtil.loggedInEmail();
        Cart cart = cartRepository.findCartByEmail(email);

        if (cart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Long cartId = cart.getCartId();
        CartDTO cartDTO = cartService.getUserCart(email, cartId);
        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<CartDTO> allCarts = cartService.getAllCarts();

        return new ResponseEntity<>(allCarts, HttpStatus.OK);
    }

    @PutMapping("/cart/products/{productId}/quantity/{operation}")
    public ResponseEntity<CartDTO> updateCartProductQuantity(@PathVariable("productId") Long productId,
                                                             @PathVariable("operation") String operation) {
        CartDTO cartDTO = cartService.updateProductQuantityInCart(productId,
                operation.equalsIgnoreCase("delete") ? -1 : 1);

        return new ResponseEntity<>(cartDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<String> deleteProductFromCart(@PathVariable("cartId") Long cartId,
                                                        @PathVariable("productId") Long productId) {

        String status = cartService.deleteProductFromCart(cartId, productId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }


}
