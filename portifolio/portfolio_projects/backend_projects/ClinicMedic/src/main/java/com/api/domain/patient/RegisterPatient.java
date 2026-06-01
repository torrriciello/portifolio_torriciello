package com.api.domain.patient;

import com.api.domain.address.DateAdress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * Data Transfer Object (Record) for patient registration.
 * Includes strict validation for personal data and nested address objects.
 */
public record RegisterPatient(
                @NotBlank(message = "Name is required") String name,

                @NotBlank(message = "Email is required") @Email(message = "Invalid email format") String email,

                @NotBlank(message = "Phone is required") String phone,

                @NotBlank(message = "CPF is required") @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "CPF must follow the format XXX.XXX.XXX-XX") String cpf,

                @NotNull(message = "Address is mandatory") @Valid // Triggers validation on the fields inside DateAdress
                DateAdress address) {
}