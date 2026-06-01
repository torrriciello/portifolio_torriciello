package com.api.domain.patient;

import com.api.domain.address.DateAdress;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (Record) used for partial updates of a Patient.
 * The 'id' field is mandatory to identify which record will be modified.
 */
public record UpdatePatient(
                @NotNull(message = "Patient ID is required for the update operation") Long id,

                String name,

                String telefone, // Note: Consider changing to 'phone' to match your Entity field name

                DateAdress address) {
}