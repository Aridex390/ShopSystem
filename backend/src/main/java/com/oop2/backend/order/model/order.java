package com.oop2.backend.order.model;

import com.oop2.backend.order.model.enums.Status;
import com.oop2.backend.order.model.enums.StatusPayment;
import com.oop2.backend.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


/**
 * This model class hold the information about an order and has a reference to @{@link Cart}.
 *
 * @author Florian Reining
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "oder")
public class Order {
    /** The unique identifier of an order */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** The date of order */
    private LocalDateTime orderDate = LocalDateTime.now();
    /** The expiry date for the payment */
    private LocalDate expireDate = orderDate.toLocalDate().plusDays(30);
    /** The status of the order @{@link Status} */
    @Enumerated(EnumType.STRING)
    private Status status;
    /** The payment status for an order @{@link StatusPayment} */
    @Enumerated(EnumType.STRING)
    private StatusPayment paymentStatus;
    /** Association to @{@link com.oop2.backend.user.model.User} */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    /** Association to @{@link Cart} saved as an @{@link List} */
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<Cart> cart;
}
