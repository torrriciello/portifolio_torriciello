package com.api.domain.consultation.validations.scheduling;

import com.api.domain.consultation.DetailsConsultation;

/**
 * Interface that defines the contract for appointment scheduling business
 * rules.
 * Implementing classes must provide specific validation logic to be executed
 * before
 * a consultation is persisted.
 */
public interface AppointmentSchedulingValidator {

    /**
     * Validates the scheduling request against specific business requirements.
     * 
     * @param data DTO containing the appointment details (doctor, patient, date).
     * @throws ValidationException if any business rule is violated.
     */
    void validate(DetailsConsultation data);

}