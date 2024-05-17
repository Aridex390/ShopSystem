package com.oop2.backend.order.repo;

import com.oop2.backend.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface hold the methods for special operation in a service and is extended by @{@link JpaRepository} for basic database operations.
 *
 * @author Florian Reining
 * @version 1.0
 */
public interface OrderRepo extends JpaRepository<Order, Long> {
    void updateOrder(Order order);
}
