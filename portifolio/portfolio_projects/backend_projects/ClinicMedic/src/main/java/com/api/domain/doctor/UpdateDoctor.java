package com.api.domain.doctor;

import com.api.domain.address.DateAdress;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (Record) for updating existing doctor information.
 * All fields except 'id' are optional to allow partial updates.
 */
public record UpdateDoctor(
        @NotNull(message = "Doctor ID is required for updates") Long id,

        String name,

        String phone,

        DateAdress address) {
}