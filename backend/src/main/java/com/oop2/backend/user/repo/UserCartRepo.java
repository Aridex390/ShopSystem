package com.oop2.backend.user.repo;

import com.oop2.backend.user.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface hold the methods for special operation in a service and is extended by @{@link JpaRepository} for basic database operations.
 *
 * @author Florian Reining
 * @version 1.0
 */
public interface UserCartRepo extends JpaRepository<UserCart, Long> {
}
