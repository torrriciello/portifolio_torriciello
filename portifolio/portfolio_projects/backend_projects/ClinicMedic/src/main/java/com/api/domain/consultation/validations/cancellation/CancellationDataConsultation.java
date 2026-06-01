package com.api.domain.consultation.validations.cancellation;

import com.api.domain.consultation.ReasonCancellation;
import jakarta.validation.constraints.NotNull;

/**
 * Data Transfer Object (Record) representing the required data to cancel a
 * consultation.
 */
public record CancellationDataConsultation(

                @NotNull(message = "Consultation ID is mandatory") Long idConsultation,

                @NotNull(message = "Cancellation reason must be provided") ReasonCancellation reasonCancellation) {
}