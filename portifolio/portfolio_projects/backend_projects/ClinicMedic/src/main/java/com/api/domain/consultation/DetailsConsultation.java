package com.api.domain.consultation;

import java.time.LocalDateTime;
import com.api.domain.doctor.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (Record) for appointment scheduling requests.
 * It contains the necessary information to identify the participants and the
 * schedule.
 */
public record DetailsConsultation(
        // Optional: If null, the system will pick a random doctor of the specified
        // specialty
        Long idDoctor,

        @NotNull(message = "Patient ID is mandatory") Long idPatient,

        @NotNull(message = "Appointment date is mandatory") @Future(message = "The appointment date must be in the future") LocalDateTime date,

        // Required only if idDoctor is null
        Specialty specialty) {
}