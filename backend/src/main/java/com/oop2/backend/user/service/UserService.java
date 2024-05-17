package com.oop2.backend.user.service;

import com.oop2.backend.user.model.Enum.Role;
import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.repo.UserCartRepo;
import com.oop2.backend.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserService {
    /** Dependency to @{@link com.oop2.backend.user.repo.UserRepo} */
    private final UserRepo userRepo;
    /** Dependency to @{@link com.oop2.backend.user.repo.UserCartRepo} */
    private final UserCartRepo userCartRepo;

    @Autowired
    public UserService(UserRepo userRepo, UserCartRepo userCartRepo) {
        this.userRepo = userRepo;
        this.userCartRepo = userCartRepo;
    }

    /**
     * The method select every product from the user cart.
     *
     * @param user takes a complete @{@link User}.
     * @return a list of the products saved in a cart.
     */
    public List<UserCart> getUserCart(User user) {
        List<UserCart> userCarts = new ArrayList<>();
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
     * @param user takes a complete @{@link User}.
     * @param userCart takes a complete @{@link UserCart}.
     * @return the new cart List for the user
     */
    public List<UserCart> addUserCart(User user, UserCart userCart) {
        List<UserCart> userCarts = getUserCart(user);
        UserCart userCart1;
        for (UserCart userCart2 : userCarts) {
            if(userCart2.equals(userCart)) {
                userCart1 = increaseQuantity(userCart);
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
     * This method creates a new @{@link User}. But before the Email will check, to avoid
     * duplicate Emails.
     *
     * @param user takes all currently saved Information's about the @{@link User}.
     * @return the new added user for an auto login after the sign-up process.
     * Or null if the Email of the User is taken.
     */
    public User addUser(User user) {
        if (!userRepo.existsByEmail(user.getEmail())) {
            return userRepo.save(user);
        }
        return null;
    }

    /**
     * This method increase the quantity by 1.
     *
     * @param userCart Takes the complete @{@link UserCart}
     * @return the product as @{@link UserCart} with the increased quantity.
     */
    public UserCart increaseQuantity(UserCart userCart) {
        int quantity = userCart.getQuantity();
        quantity++;
        userCart.setQuantity(quantity);
        return userCartRepo.save(userCart);
    }

    /**
     * This methode decrease the quantity by 1.
     *
     * @param userCart Takes the complete @{@link UserCart}
     * @return the product as @{@link UserCart} with the decreased quantity.
     */
    public UserCart decreaseQuantity(UserCart userCart) {
        int quantity = userCart.getQuantity();
        quantity--;
        if (quantity <= 0) {
            deleteUserCart(userCart);
            return null;
        } else {
            userCart.setQuantity(quantity);
            return userCartRepo.save(userCart);
        }
    }

    /**
     *
     * @param userCart Takes the complete @{@link UserCart}
     * @return the new list of products in a cart.
     */
    public List<UserCart> deleteUserCart(UserCart userCart) {
        userCartRepo.delete(userCart);
        User user = userCart.getUser();

        return getUserCart(getUserById(user.getId()));
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
     * The methode delete a user and his cart.
     *
     * @param user takes a complete @{@link User}.
     */
    public void deleteUser(User user) {
        userCartRepo.deleteAllByUser(user);
        userRepo.delete(user);
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
