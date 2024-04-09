package com.oop2.backend.model;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * This model class holds information's about the shop from a user.
 *
 * @author Florian Reining
 * @version 1.0
 */
@Entity
public class Shop implements Serializable {
    /** Unique identifier of the Shop */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, name = "id")
    protected Long id;
    @Column(unique = true, nullable = false)
    /** Name of the Shop */
    protected String shopName;
    /** logo URL of the Shop */
    protected String logoUrl;
    /** Reference to the User */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    protected User user;

    public Shop() {
    }

    /**
     * This contractor only is used when the shop creator do not have a Logo URL.
     * Passes a default logo.
     * @param shopName the name of the Shop
     */
    public Shop(String shopName, User user) {
        new Shop(shopName, "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.creativefabrica.com%2Fde%2Fproduct%2Fmountain-logo-design-52%2F&psig=AOvVaw2ypsGaPGZ5ubZzAH-amlcV&ust=1712784756916000&source=images&cd=vfe&opi=89978449&ved=0CBIQjRxqFwoTCKjdhcqKtoUDFQAAAAAdAAAAABAJ",
                user);
    }

    public Shop(String shopName, String logoUrl, User user) {
        this.shopName = shopName;

        this.logoUrl = logoUrl;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
