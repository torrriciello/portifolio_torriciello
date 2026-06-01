package com.api.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class to define the password hashing algorithm.
 * Using BCrypt ensures that even if the database is compromised,
 * user passwords remain secure.
 */
@Configuration
public class EncoderConfig {

    /**
     * Provides a BCryptPasswordEncoder bean to the Spring Context.
     * Spring Security will use this to match raw passwords from login
     * with the hashed versions in the database.
     * * @return An instance of BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}