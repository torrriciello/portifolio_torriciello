package com.api.domain.consultation.validations.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.api.domain.ValidationException;
import com.api.domain.consultation.ConsultationRepository;
import com.api.domain.consultation.DetailsConsultation;

/**
 * Validator to prevent a doctor from having two appointments at the same time.
 * This ensures that double-booking does not occur for medical staff.
 */
@Component
public class ValidatorMedicoComOutraConsultaNoSameTime implements AppointmentSchedulingValidator {

    @Autowired
    private ConsultationRepository consultationRepository;

    /**
     * Validates if the selected doctor is already booked for the requested date and
     * time.
     * 
     * @param data DTO containing the doctor's ID and the proposed appointment
     *             date/time.
     * @throws ValidationException if the doctor already has an active
     *                             (non-cancelled) appointment at that time.
     */
    public void validate(DetailsConsultation data) {
        // Checks the database for any existing appointment for this doctor at the same
        // time
        // that hasn't been cancelled (reasonCancellation is null).
        var doctorHasAnotherConsultationAtTheSameTime = consultationRepository
                .existsByDoctorIdAndDateAndReasonCancellationIsNull(data.idDoctor(), data.date());

        if (doctorHasAnotherConsultationAtTheSameTime) {
            throw new ValidationException(
                    "The selected doctor already has another appointment scheduled for this time slot.");
        }
    }

}