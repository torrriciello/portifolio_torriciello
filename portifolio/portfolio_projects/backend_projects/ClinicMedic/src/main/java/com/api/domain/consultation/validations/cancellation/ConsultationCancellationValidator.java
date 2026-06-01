package com.api.domain.consultation.validations.cancellation;

/**
 * Interface that defines the contract for consultation cancellation validators.
 * Any new business rule for cancellation should implement this interface.
 */
public interface ConsultationCancellationValidator {

    /**
     * Validates the cancellation request based on specific business rules.
     * 
     * @param data DTO containing the consultation ID and the reason for
     *             cancellation.
     * @throws ValidationException if the cancellation violates a business rule.
     */
    void validate(CancellationDataConsultation data);

}