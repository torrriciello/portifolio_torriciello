package com.api.domain.user;

import com.api.domain.address.Address;
import com.api.domain.role.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO for User registration.
 * Collects personal data, security credentials, and initial role assignments.
 */
@Getter
@Setter
public class RegisterUser {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "CPF is required")
    private String cpf;

    @NotNull(message = "Role must be assigned")
    private Role role;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Address is mandatory")
    @Valid // Cascades validation to the Address entity/DTO
    private Address address;

}