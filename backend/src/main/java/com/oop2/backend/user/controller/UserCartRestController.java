package com.oop2.backend.user.controller;

import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.service.UserCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<UserCart>> getUserCart(User user) {
        List<UserCart> cart = userCartService.getUserCart(user);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping(value = "/increase")
    public ResponseEntity<UserCart> increaseUserCart(UserCart userCart) {
        UserCart cartItem = userCartService.increaseQuantity(userCart);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping(value = "/decrease")
    public ResponseEntity<UserCart> decreaseUserCart(UserCart userCart) {
        UserCart cartItem = userCartService.decreaseQuantity(userCart);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping(value = "/add")
    public ResponseEntity<List<UserCart>> addUserCart(UserCart userCart, User user) {
        List<UserCart> newCartItem = userCartService.addUserCart(user, userCart);
        return new ResponseEntity<>(newCartItem, HttpStatus.OK);
    }
}
