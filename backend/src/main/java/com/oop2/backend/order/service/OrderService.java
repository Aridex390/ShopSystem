package com.oop2.backend.order.service;

import com.oop2.backend.order.exeption.OrderNotFoundExeption;
import com.oop2.backend.order.model.Cart;
import com.oop2.backend.order.model.Order;
import com.oop2.backend.order.model.enums.Status;
import com.oop2.backend.order.model.enums.StatusPayment;
import com.oop2.backend.order.repo.CartRepo;
import com.oop2.backend.order.repo.OrderRepo;
import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.repo.UserCartRepo;
import com.oop2.backend.user.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This service class handles all the action for an @{@link Order} and his @{@link Cart}.
 * </p>
 * The Order and the cart cant be edited after creation,
 * except the status.
 *
 * @author Florian Reining
 * @version 1.2
 */
@Service
public class OrderService {
    /** Dependency to @{@link OrderRepo} */
    private final OrderRepo orderRepo;
    /** Dependency to @{@link CartRepo} */
    private final CartRepo cartRepo;
    /** Dependency to @{@link UserRepo} */
    private final UserRepo userRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, CartRepo cartRepo, UserRepo userRepo) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
    }

    /**
     * The methode create a new order
     *
     * @param carts takes the @{@link UserCart} to save it to an @{@link Order}
     * @return the saved order
     */
    public Order addOrder(List<UserCart> carts) {
        UserCart userCart = carts.get(0);
        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .expireDate(LocalDate.now().plusDays(30))
                .status(Status.OPEN)
                .paymentStatus(StatusPayment.OPEN)
                .user(userRepo.findByEmail(
                        userCart.getUser().getEmail())
                        .orElseThrow(() -> new UsernameNotFoundException("User with the email " + userCart.getUser().getEmail() + " was not found")))
                .build();

        order = orderRepo.save(order);

        for (UserCart cart : carts) {
            Cart cart1 = Cart.builder()
                    .order(order)
                    .product(cart.getProduct())
                    .quantity(cart.getQuantity())
                    .build();

            cartRepo.save(cart1);
        }

        return order;
    }

    /**
     * The methode looks for all orders that associated to a user.
     *
     * @param email takes a @{@link String}
     * @return all orders for a user as a list
     */
    public List<Order> findeAllOrderForUser(String email) {
        List<Order> orders = new ArrayList<>();
        User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with the email " + email + " was not found."));
        for (Order order : orderRepo.findAll()) {
            if (order.getUser().equals(user)) {
                orders.add(order);
            }
        }
        return orders;
    }

    /**
     *
     * @param id takes the id of an order
     * @param email takes a @{@link String}
     * @return the order for a user or null if no order exist with the id
     */
    public Order findOrderByIdForUser(Long id, String email) {
        List<Order> orders = findeAllOrderForUser(email);
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return order;
            }
        }
        return null;
    }

    /**
     * The methode updates the status of an order.
     * </p>
     * Possible status information's are in @{@link com.oop2.backend.order.model.enums.Status}
     *
     * @param order takes an @{@link Order}
     */
    public Order updateOrder(Order order) {
        return orderRepo.save(order);
    }

    /**
     * This methode checks the current payment status of an order.
     *
     * @param order takes a complete order
     * @return returns the current payment status
     */
    public StatusPayment checkPayment(Order order) {
        if(order.getPaymentStatus().equals(StatusPayment.PAYED)) {
            return StatusPayment.PAYED;
        } else if (order.getPaymentStatus().equals(StatusPayment.OPEN) && order.getExpireDate().isEqual(LocalDate.now()) || order.getExpireDate().isAfter(LocalDate.now())) {
            order.setPaymentStatus(StatusPayment.PAY_WARN);
            updateOrder(order);
            return StatusPayment.PAY_WARN;
        } else if(order.getPaymentStatus().equals(StatusPayment.OPEN)) {
            return StatusPayment.OPEN;
        } else {
            return StatusPayment.PAY_WARN;
        }
    }

}
