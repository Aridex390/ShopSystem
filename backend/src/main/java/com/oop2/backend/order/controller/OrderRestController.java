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

    @GetMapping(value = "/{email}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable String email) {
        List<Order> orders = orderService.findeAllOrderForUser(email);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}?user={email}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id, @PathVariable String email) {
        Order order = orderService.findOrderByIdForUser(id, email);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Order> addOrder(@RequestBody List<UserCart> userCart) {
        Order newOrder = orderService.addOrder(userCart);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        Order updatedOrder = orderService.updateOrder(order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

}
