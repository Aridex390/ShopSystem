package com.oop2.backend.user.controller;

import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("cart")
public class UserCartRestController {
    private final UserService userService;

    @Autowired
    public UserCartRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<UserCart> getUserCart(User user) {
        return userService.getUserCart(user);
    }

    @RequestMapping(value = "/increase", method = RequestMethod.PUT, consumes = "application/json")
    public void increaseUserCart(UserCart userCart) {
        userService.increaseQuantity(userCart);
    }

    @RequestMapping(value = "/decrease", method = RequestMethod.PUT, consumes = "application/json")
    public void decreaseUserCart(UserCart userCart) {
        userService.decreaseQuantity(userCart);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public void addUserCart(UserCart userCart, User user) {
        userService.addUserCart(user, userCart);
    }
}
