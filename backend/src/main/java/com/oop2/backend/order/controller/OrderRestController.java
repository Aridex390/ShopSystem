package com.oop2.backend.order.controller;

import com.oop2.backend.order.model.Order;
import com.oop2.backend.order.service.OrderService;
import com.oop2.backend.user.model.User;
import com.oop2.backend.user.model.UserCart;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The rest controller class for order, with the endpoints to exchange data between the frontend and the backend.
 *
 * @author Florian Reining
 * @version 1.1
 */
@RestController
@RequestMapping("/user/order")
@AllArgsConstructor
public class OrderRestController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(User user) {
        List<Order> orders = orderService.findeAllOrderForUser(user);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id, User user) {
        Order order = orderService.findOrderByIdForUser(id, user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, List<UserCart> userCart) {
        Order newOrder = orderService.addOrder(order, userCart);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }



}
