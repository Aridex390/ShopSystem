package com.oop2.backend.order.controller;

import com.oop2.backend.order.model.Order;
import com.oop2.backend.order.service.OrderService;
import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The rest controller class for order, with the endpoints to change data between the frontend and the backend.
 *
 * @author Florian Reining
 * @version 1.0
 */
@RestController("order")
public class OrderRestController {

    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET, produces = "application/json")
    public List<Order> getOrders(User user) {
        return orderService.findeAllOrderForUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Order getOrder(@PathVariable Long id, User user) {
        return orderService.findOrderByIdForUser(id, user);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public void addOrder(@RequestBody Order order, List<UserCart> userCart) {
        orderService.addOrder(order, userCart);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json")
    public void updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
    }



}
