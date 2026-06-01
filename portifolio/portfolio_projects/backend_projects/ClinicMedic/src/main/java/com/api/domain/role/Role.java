package com.api.domain.role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a security role in the database.
 * This entity maps the ERole constants to a persistent table,
 * allowing for fine-grained access control.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, unique = true)
    private Erole name;

    /**
     * Convenience constructor to create a Role from the ERole enum.
     */
    public Role(Erole name) {
        this.name = name;
    }
}