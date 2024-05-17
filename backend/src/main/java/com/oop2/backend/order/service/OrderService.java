package com.oop2.backend.order.service;

import com.oop2.backend.order.model.Cart;
import com.oop2.backend.order.model.Order;
import com.oop2.backend.order.model.enums.StatusPayment;
import com.oop2.backend.order.repo.CartRepo;
import com.oop2.backend.order.repo.OrderRepo;
import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import com.oop2.backend.user.repo.UserCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This service class handles all the action for an @{@link Order} and his @{@link Cart}.
 * </p>
 * The Order and the cart cant be edited after creation,
 * except the status.
 *
 * @author Florian Reining
 * @version 1.1
 */
@Service
public class OrderService {
    /** Dependency to @{@link OrderRepo} */
    private final OrderRepo orderRepo;
    /** Dependency to @{@link CartRepo} */
    private final CartRepo cartRepo;
    /** Dependency to @{@link UserCartRepo} */
    private final UserCartRepo userCartRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, CartRepo cartRepo, UserCartRepo userCartRepo) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
        this.userCartRepo = userCartRepo;
    }

    /**
     * The methode create a new order
     *
     * @param order takes currently available informations about a order
     * @param carts takes the user cart to save it to an order
     * @return the saved order
     */
    public Order addOrder(Order order, List<UserCart> carts) {
        for (UserCart cart : carts) {
            Cart cart1 = new Cart(cart.getQuantity(), order, cart.getProduct());
            cartRepo.save(cart1);
            userCartRepo.delete(cart);
        }
        return orderRepo.save(order);

    }

    /**
     * The methode looks for all orders that associated to a user.
     *
     * @param user takes a @{@link User}
     * @return all orders for a user as a list
     */
    public List<Order> findeAllOrderForUser(User user) {
        List<Order> orders = new ArrayList<>();
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
     * @param user takes a @{@link User}
     * @return the order for a user or null if no order exist with the id
     */
    public Order findeOrderByIdForUser(int id, User user) {
        List<Order> orders = findeAllOrderForUser(user);
        for (Order order : orders) {
            if (order.getId() == id) {
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
    public void updateOrder(Order order) {
        orderRepo.updateOrder(order);
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
