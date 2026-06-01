package com.api.domain.user.auth;

import jakarta.validation.constraints.NotBlank;

public record PasswordResetData(

        @NotBlank String token,

        @NotBlank String newPassword

) {
}