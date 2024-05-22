package com.oop2.backend.user.controller;

import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cart")
public class UserCartRestController {
    private final UserService userService;

    @Autowired
    public UserCartRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserCart>> getUserCart(User user) {
        List<UserCart> cart = userService.getUserCart(user);
        return new ResponseEntity<>(cart, HttpStatus.OK)
    }

    @PutMapping(value = "/increase")
    public ResponseEntity<UserCart> increaseUserCart(UserCart userCart) {
        UserCart cartItem = userService.increaseQuantity(userCart);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping(value = "/decrease")
    public ResponseEntity<UserCart> decreaseUserCart(UserCart userCart) {
        UserCart cartItem = userService.decreaseQuantity(userCart);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping(value = "/add")
    public ResponseEntity<List<UserCart>> addUserCart(UserCart userCart, User user) {
        List<UserCart> newCartItem = userService.addUserCart(user, userCart);
        return new ResponseEntity<>(newCartItem, HttpStatus.OK);
    }
}
