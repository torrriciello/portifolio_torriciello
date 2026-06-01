package com.api.domain.doctor;

import com.api.domain.address.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (Record) for registering a new doctor.
 * Includes validation constraints to ensure data integrity at the API entry
 * point.
 */
public record RegisterDoctor(

                @NotBlank(message = "Name is required") String name,

                @NotBlank(message = "CPF is required") String cpf,

                @NotBlank(message = "CRM (Medical License) is required") String crm,

                @NotBlank(message = "Phone number is required") String phone,

                @NotNull(message = "Specialty is mandatory") Specialty especialidade,

                @NotNull(message = "Address data is mandatory") @Valid // Ensures the fields inside the Address object
                                                                       // are also validated
                Address address) {
}