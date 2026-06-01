package com.api.domain.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.domain.ValidationException;

/**
 * Service responsible for user management, including registration with
 * password encoding and uniqueness validation for sensitive data.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Retrieves a user by their email.
     */
    public User findByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the provided email."));
    }

    /**
     * Registers a new user.
     */
    @Transactional
    public User save(RegisterUser data) {

        if (repository.findByEmail(data.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered!");
        }

        if (repository.findByCpf(data.getCpf()).isPresent()) {
            throw new ValidationException("CPF already registered!");
        }

        String senhaCriptografada = passwordEncoder.encode(data.getPassword());

        User newUser = new User(data, senhaCriptografada);

        return repository.save(newUser);
    }

    /**
     * Generates a password reset token for the user.
     */
    @Transactional
    public void generatePasswordResetToken(String email) {

        User user = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);
        user.setResetTokenExpiration(LocalDateTime.now().plusMinutes(30));

        repository.save(user);
    }

    /**
     * Resets the user's password using the reset token.
     */
    @Transactional
    public void resetPassword(String token, String newPassword) {

        User user = repository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        if (user.getResetTokenExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expired");
        }

        String encryptedPassword = passwordEncoder.encode(newPassword);

        user.setPassword(encryptedPassword);
        user.setResetToken(null);
        user.setResetTokenExpiration(null);

        repository.save(user);
    }
}