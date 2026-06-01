package com.api.domain.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.api.domain.address.Address;
import com.api.domain.role.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Core User entity integrated with Spring Security's UserDetails.
 * This class handles both identity persistence and authentication authorities.
 */
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String resetToken;

    private LocalDateTime resetTokenExpiration;

    @NotNull
    @Column(unique = true) // Crucial for authentication via email
    private String email;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Security: Never expose hashes in JSON responses
    private String password;

    @ManyToOne(fetch = FetchType.EAGER) // Roles are usually needed immediately for authorization
    @JoinColumn(name = "role_id")
    private Role role;

    @Embedded
    @NotNull(message = "Address is mandatory")
    @Valid
    private Address address;

    @NotNull
    @CPF
    @Column(unique = true)
    private String cpf;

    /**
     * Custom constructor for registration logic.
     * 
     * @param data              DTO with user information.
     * @param encryptedPassword The BCrypt hashed password provided by the service.
     */
    public User(RegisterUser data, String encryptedPassword) {
        this.name = data.getName();
        this.email = data.getEmail();
        this.password = encryptedPassword;
        this.cpf = data.getCpf();
        this.role = data.getRole();
        this.address = data.getAddress();
    }

    /**
     * Converts the internal Role entity into a Spring Security Authority.
     * Uses the 'ROLE_' prefix for standard RBAC support.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null || this.role.getName() == null)
            return List.of();
        // Maps ERole name to SimpleGrantedAuthority
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.role.getName().name()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return email; // Email acts as the unique identifier for login
    }

    // Default UserDetails status flags (returning true for simplicity)
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