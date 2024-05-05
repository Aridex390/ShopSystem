package com.oop2.backend.Product.model;

import com.oop2.backend.Product.model.Enums.Currency;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class ist the hold the Information's about a product
 *
 * @author Florian Reining
 * @version 1.0
 */
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
    private Currency currency = Currency.DOLLAR;
    /** Association to @{@link ProductConfig} and saved as a @{@link Set} */
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private Set<ProductConfig> productConfigs = new LinkedHashSet<>();

    public Product() {
    }

    public Product(String name, double weight, String description, double price, Currency currency, Set<ProductConfig> productConfigs) {
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.productConfigs = productConfigs;
    }

    public Product(String name, String description, double weight, double price, Set<ProductConfig> productConfigs) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.productConfigs = productConfigs;
    }

    public Set<ProductConfig> getProductConfigs() {
        return productConfigs;
    }

    public void setProductConfigs(Set<ProductConfig> productConfigs) {
        this.productConfigs = productConfigs;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

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
                ", productConfigs=" + productConfigs +
                ", id=" + id +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}