package com.oop2.backend.order.repo;

import com.oop2.backend.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface hold the methods for special operation in a service and is extended by @{@link JpaRepository} for basic database operations.
 *
 * @author Florian Reining
 * @version 1.1
 */
public interface OrderRepo extends JpaRepository<Order, Long> {
    Optional<Order> updateOrder(Order order);
}
