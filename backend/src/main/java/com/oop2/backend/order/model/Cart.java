package com.oop2.backend.order.model;

import com.oop2.backend.Product.model.Product;
import jakarta.persistence.*;
import lombok.*;


/**
 * This model class holds the information about
 * the products for an order.
 *</p>
 * The key consists of the order key and the product key
 *
 * @author Florian Reining
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "order_cart")
public class Cart {
    /** the unique identifier */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** the Quantity of a Product */
    private int quantity;
    /** Association to @{@link Order} */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    /** Association to @{@link com.oop2.backend.Product.model.Product} */
    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Cart(int quantity, Order order, Product product) {
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }
}
