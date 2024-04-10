package com.oop2.backend.model;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * This model class holds all information's about the User how can order somthing
 *
 * @author Florian Reining
 * @version 1.0
 */
@Entity
public class User implements Serializable {
    /** unique identifier of the User */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false, name = "id")
    protected Long id;
    /** Firstname of the User */
    @Column(nullable = false)
    protected String firstname;
    /** Lastname of the User */
    @Column(nullable = false)
    protected String lastname;
    /** Email of the User */
    @Column(nullable = false)
    protected String email;
    @Column(nullable = false)
    /** Phone number of the User */
    protected String phoneNumber;
    /** Username of the User */
    @Column(unique = true, nullable = false)
    protected String username;
    /** password of the User */
    @Column(nullable = false)
    protected String password;
    /** is the User active / used */
    @Column(nullable = false)
    protected boolean isUserActive;
    /** has an active shop */
    @Column(nullable = false)
    protected boolean hasActiveShop = false;
    /** Reference to the shop */
    /*@OneToOne(mappedBy = "id")
    protected Shop shop;*/

    public User() {
    }

    public User(String firstname, String lastname, String email, String phoneNumber, String username, String password,
                boolean isUserActive, boolean hasActiveShop/*, Shop shop*/) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.isUserActive = isUserActive;
        this.hasActiveShop = hasActiveShop;
        //this.shop = shop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUserActive() {
        return isUserActive;
    }

    public void setUserActive(boolean userActive) {
        isUserActive = userActive;
    }

    public boolean isHasActiveShop() {
        return hasActiveShop;
    }

    public void setHasActiveShop(boolean hasActiveShop) {
        this.hasActiveShop = hasActiveShop;
    }
/*
    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }
*/
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isUserActive=" + isUserActive +
                ", hasActiveShop=" + hasActiveShop +
                '}';
    }
}
