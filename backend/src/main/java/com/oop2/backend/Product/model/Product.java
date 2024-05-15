package com.oop2.backend.Product.model;

import com.oop2.backend.Product.model.Enums.Currency;
import com.oop2.backend.order.model.Cart;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.util.Objects;


/**
 * This class ist the hold the Information's about a product
 *
 * @author Florian Reining
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    /** Unique identifier for a product */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** Name for the product @{@link String} */
    private String name;
    /** Description for the product @{@link String} */
    private String description;
    /** Weight for the product (grams) @{@link Double} */
    private double weight = 100;
    /** Price for the product (0.00 €/$) @{@link Double}*/
    private double price;
    /** Currency for the product @{@link Currency}*/
    @Enumerated(EnumType.STRING)
    private Currency currency = Currency.DOLLAR;
    /** Association to @{@link com.oop2.backend.order.model.Cart} */
    @OneToOne(mappedBy = "product")
    private Cart cart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", price=" + price +
                ", currency=" + currency +
                ", id=" + id +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}