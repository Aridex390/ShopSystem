package com.oop2.backend.user.repo;

import com.oop2.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface hold the methods for special operation in a service and is extended by @{@link JpaRepository} for basic database operations.
 *
 * @author Florian Reining
 * @version 1.0
 */
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String username);
}
