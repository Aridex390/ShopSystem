package com.oop2.backend.order.service;

import com.oop2.backend.order.model.Order;
import com.oop2.backend.order.repo.CartRepo;
import com.oop2.backend.order.repo.OrderRepo;
import com.oop2.backend.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// TODO: Documentation
@Service
public class OrderService {
    private final OrderRepo orderRepo;
    private final CartRepo cartRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, CartRepo cartRepo) {
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
    }

    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    public List<Order> findeAllOrderForUser(User user) {
        List<Order> orders = new ArrayList<>();
        for (Order order : orderRepo.findAll()) {
            if (order.getUser().equals(user)) {
                orders.add(order);
            }
        }
        return orders;
    }

    public Order findeOrderByIdForUser(int id, User user) {
        List<Order> orders = findeAllOrderForUser(user);
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public void updateOrder(Order order) {
        orderRepo.updateOrder(order);
    }

}
