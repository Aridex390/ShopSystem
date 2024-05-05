package com.oop2.backend.Product.model;

import com.oop2.backend.Product.model.Product;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

/**
 * This class holds the information's about the
 * product configuration-options.
 * One product @{@link Product} can have several configuration-options.
 * @author Florian Reining
 * @version 1.0
 */
@Entity
@Table(name = "product_config")
public class ProductConfig {
    /** Unique identifier for the Config @{@link Long} */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** Name for the Config. Like the color or the length / size. */
    private String name;
    /** The price for a product with this config (Leave the Price empty for the default product price @{@link Product}) */
    private double price;
    /** The weight for a product with this config (Leave the weight empty for the default product weight @{@link Product}) */
    private double weight;
    /** Association to @{@link Product} to know the product of the config*/
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductConfig() {
    }

    public ProductConfig(String name, double price, double weight, Product product) {
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.product = product;
    }

    public ProductConfig(String name, Product product) {
        this.name = name;
        this.product = product;
        this.price = product.getPrice();
        this.weight = product.getWeight();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}