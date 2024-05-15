package com.oop2.backend.order.repo;

import com.oop2.backend.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO: Documentation
public interface OrderRepo extends JpaRepository<Order, Long> {
    void updateOrder(Order order);
}
