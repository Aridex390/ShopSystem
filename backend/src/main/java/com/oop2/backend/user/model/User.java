package com.oop2.backend.user.model;

import com.oop2.backend.order.model.Order;
import com.oop2.backend.user.model.Enum.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * This model class hold the information's about a user.
 * Like the user credentials and the address.
 * <p>
 * To keep the shop simple, the saved address is used for
 * invoice and delivery address.
 *
 * @author Florian Reining
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
    /** the unique identifier for the user */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /** the firstname of the user */
    private String firstname;
    /** the lastname of the user */
    private String lastname;
    /** the Username / Email of the user @{@link jakarta.persistence.Column} */
    private String email;
    /** the password of the user. will be hashed for the database later on */
    private String password;
    /** the role of the user. To decide of he / she has access to the admin page */
    @Enumerated(EnumType.STRING)
    private Role role;
    /** Association to the user cart @{@link com.oop2.backend.user.model.UserCart} */
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @Builder.Default
    private List<UserCart> userCarts = new ArrayList<>();
    /** Association to the @{@link Order} as @{@link List} */
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @Builder.Default
    private List<Order> orders = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
