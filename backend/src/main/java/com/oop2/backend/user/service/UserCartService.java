package com.oop2.backend.user.service;

import com.oop2.backend.user.exception.UserCartNotfoundException;
import com.oop2.backend.user.model.Enum.Role;
import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.repo.UserCartRepo;
import com.oop2.backend.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This service class handles all the action for a @{@link User} and his @{@link UserCart}.
 *
 * @author Florian Reining
 * @version 1.1
 */
@Service
public class UserCartService {
    /** Dependency to @{@link com.oop2.backend.user.repo.UserRepo} */
    private final UserRepo userRepo;
    /** Dependency to @{@link com.oop2.backend.user.repo.UserCartRepo} */
    private final UserCartRepo userCartRepo;

    @Autowired
    public UserCartService(UserRepo userRepo, UserCartRepo userCartRepo) {
        this.userRepo = userRepo;
        this.userCartRepo = userCartRepo;
    }

    /**
     * The method select every product from the user cart.
     *
     * @param email takes @{@link String}
     * @return a list of the products saved in a cart.
     */
    public List<UserCart> getUserCart(String email) {
        List<UserCart> userCarts = new ArrayList<>();
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with the email " + email + " was not found."));
        for (UserCart userCart : userCartRepo.findAll()) {
            if (userCart.getUser().equals(user)) {
                userCarts.add(userCart);
            }
        }
        return userCarts;
    }

    /**
     * This method add a new product to the card if its doesn't
     * exist at the moment.
     * </p> If the product is currently added, the quantity of the product
     * will be increased by 1 with @{@systemProperty increaseQuantity}.
     *
     * @param email takes a @{@link String}.
     * @param userCart takes a complete @{@link UserCart}.
     * @return the new cart List for the user
     */
    public List<UserCart> addUserCart(String email, UserCart userCart) {
        List<UserCart> userCarts = getUserCart(email);
        UserCart userCart1;
        for (UserCart userCart2 : userCarts) {
            if(userCart2.equals(userCart)) {
                userCart1 = increaseQuantity(userCart.getId());
                userCarts.remove(userCart);
                userCarts.add(userCart1);
            } else {
                userCarts.add(userCart);
                userCartRepo.save(userCart);
            }
        }
        return userCarts;
    }

    /**
     * This method increase the quantity by 1.
     *
     * @param id Takes the complete @{@link Long}
     * @return the product as @{@link UserCart} with the increased quantity.
     */
    public UserCart increaseQuantity(Long id) {
        UserCart userCart = userCartRepo.findById(id).orElseThrow(() -> new UserCartNotfoundException("Cart item with id " + id + " was not found."));
        int quantity = userCart.getQuantity();
        quantity++;
        userCart.setQuantity(quantity);
        return userCartRepo.save(userCart);
    }

    /**
     * This methode decrease the quantity by 1.
     *
     * @param id Takes w @{@link Long}
     * @return the product as @{@link UserCart} with the decreased quantity.
     */
    public UserCart decreaseQuantity(Long id) {
        UserCart userCart = userCartRepo.findById(id).orElseThrow(() -> new UserCartNotfoundException("Cart item with id " + id + " was not found."));
        int quantity = userCart.getQuantity();
        quantity--;
        if (quantity <= 0) {
            removeFromUserCart(userCart.getId());
            return null;
        } else {
            userCart.setQuantity(quantity);
            return userCartRepo.save(userCart);
        }
    }

    /**
     *
     * @param id Takes the complete @{@link Long}
     * @return the new list of products in a cart.
     */
    public List<UserCart> removeFromUserCart(Long id) {
        userCartRepo.deleteById(id);
        UserCart userCart =  userCartRepo.findById(id).orElseThrow(() -> new UserCartNotfoundException("Cart item with id " + id + " was not found."));
        User user = userCart.getUser();

        return getUserCart(user.getEmail());
    }

    /**
     * The methode gets a User
     *
     * @param id takes the user id as a Long
     * @return a user if existing instead it returns null
     */
    private User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    /**
     * The methode check the role of User.
     *
     * @param user takes a complete @{@link User}
     * @return the @{@link Role} of a User
     */
    public Role checkRole(User user) {
        if (user.getRole().equals(Role.ADMIN)) {
            return Role.ADMIN;
        } else {
            return Role.USER;
        }
    }
}
