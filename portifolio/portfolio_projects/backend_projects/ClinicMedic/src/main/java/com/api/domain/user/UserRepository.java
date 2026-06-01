package com.api.domain.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for User entity operations.
 * Provides specialized search methods for authentication and unique constraint
 * validation.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     * Essential for the authentication process (loadUserByUsername).
     * 
     * @param email The unique email to search for.
     * @return An Optional containing the user if found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds a user by their CPF.
     * Useful for preventing duplicate registrations during the signup process.
     * 
     * @param cpf The unique CPF to search for.
     * @return An Optional containing the user if found.
     */
    Optional<User> findByCpf(String cpf);

    Optional<User> findByResetToken(String token);
}