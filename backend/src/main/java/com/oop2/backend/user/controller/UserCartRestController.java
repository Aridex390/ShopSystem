package com.oop2.backend.user.controller;

import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The rest controller class for the cart of a user, with the endpoints to exchange data between the frontend and the backend.
 * </p>
 * The controller is only for get purpose.
 * @author Florian Reining
 * @version 1.0
 */
@RestController
@RequestMapping("/user/cart")
public class UserCartRestController {
    private final UserCartService userCartService;

    @Autowired
    public UserCartRestController(UserCartService userCartService) {
        this.userCartService = userCartService;
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<List<UserCart>> getUserCart(@PathVariable String email) {
        List<UserCart> cart = userCartService.getUserCart(email);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping(value = "/increase/{id}")
    public ResponseEntity<UserCart> increaseUserCart(@PathVariable Long id) {
        UserCart cartItem = userCartService.increaseQuantity(id);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
// TODO: change parameter to {Long id}
    @PutMapping(value = "/decrease/{id}")
    public ResponseEntity<UserCart> decreaseUserCart(@PathVariable Long id) {
        UserCart cartItem = userCartService.decreaseQuantity(id);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PostMapping(value = "/add?user={email}")
    public ResponseEntity<List<UserCart>> addUserCart(UserCart userCart, @PathVariable String email) {
        List<UserCart> newCartItem = userCartService.addUserCart(email, userCart);
        return new ResponseEntity<>(newCartItem, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<List<UserCart>> deleteUserCart(@PathVariable Long id) {
        List<UserCart> userCart = userCartService.removeFromUserCart(id);
        return new ResponseEntity<>(userCart, HttpStatus.OK);
    }
}
