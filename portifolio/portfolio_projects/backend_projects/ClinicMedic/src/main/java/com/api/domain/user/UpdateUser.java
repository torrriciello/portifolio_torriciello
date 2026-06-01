package com.api.domain.user;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for updating existing User information.
 * Fields are optional to support partial updates (PATCH style).
 */
@Getter
@Setter
public class UpdateUser {

    @Email(message = "Invalid email format")
    private String email;

    private Long roleId;

    private String name;

    private String cpf;
}