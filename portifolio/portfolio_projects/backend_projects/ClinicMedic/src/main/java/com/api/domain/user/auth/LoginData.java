package com.api.domain.user.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (Record) representing authentication credentials.
 * Used to capture login attempts from the client.
 */
public record LoginData(
        @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

        @NotBlank(message = "Password is required") String password) {
}