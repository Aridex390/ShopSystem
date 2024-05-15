package com.oop2.backend.user.service;

import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.repo.UserCartRepo;
import com.oop2.backend.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
// TODO: Documentation

@Service
public class UserService {
    private final UserRepo userRepo;
    private final UserCartRepo userCartRepo;

    @Autowired
    public UserService(UserRepo userRepo, UserCartRepo userCartRepo) {
        this.userRepo = userRepo;
        this.userCartRepo = userCartRepo;
    }

    public List<UserCart> getUserCart(User user) {
        List<UserCart> userCarts = new ArrayList<>();
        for (UserCart userCart : userCartRepo.findAll()) {
            if (userCart.getUser().equals(user)) {
                userCarts.add(userCart);
            }
        }
        return userCarts;
    }

    public List<UserCart> addUserCart(User user, UserCart userCart) {
        List<UserCart> userCarts = getUserCart(user);
        UserCart userCart1;
        for (UserCart userCart2 : userCarts) {
            int i = 0;
            if(userCart2.equals(userCart)) {
                userCart1 = higherQuantity(userCart);
                userCarts.remove(userCart);
                userCarts.add(userCart1);
                userCartRepo.save(userCart);
            } else {
                userCarts.add(userCart);
                userCartRepo.save(userCart);
            }
        }
        return userCarts;
    }

    public User addUser(User user) {
        if (!userRepo.existsByEmail(user.getEmail())) {
            return userRepo.save(user);
        }
        return null;
    }

    public UserCart higherQuantity(UserCart userCart) {
        int quantity = userCart.getQuantity();
        quantity++;
        return userCart;
    }

    public UserCart lowerQuantity(UserCart userCart) {
        int quantity = userCart.getQuantity();
        quantity++;
        return userCart;
    }
}
