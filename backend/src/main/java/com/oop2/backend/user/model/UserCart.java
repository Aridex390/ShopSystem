package com.oop2.backend.user.model;

import com.oop2.backend.Product.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This model is nearly similar to the @{@link com.oop2.backend.order.model.Cart}.
 *</p>
 * the different is that this model is used to save the cart
 * only referenced to the User and can updated while shopping
 * @author Florian Reining
 * @version 1.0
 */
// TODO: Documentation
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_cart")
public class UserCart {
    /** The unique identifier for the user cart. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** the quantity of a  product. */
    private int quantity;
    /** Association to @{@link com.oop2.backend.user.model.User} */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;




}
