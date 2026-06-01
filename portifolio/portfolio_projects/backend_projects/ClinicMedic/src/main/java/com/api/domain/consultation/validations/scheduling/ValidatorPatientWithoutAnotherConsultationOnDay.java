package com.api.domain.consultation.validations.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.api.domain.ValidationException;
import com.api.domain.consultation.ConsultationRepository;
import com.api.domain.consultation.DetailsConsultation;

/**
 * Validator to ensure a patient does not have more than one appointment on the
 * same day.
 */
@Component
public class ValidatorPatientWithoutAnotherConsultationOnDay implements AppointmentSchedulingValidator {

    @Autowired
    private ConsultationRepository consultationRepository;

    /**
     * Validates if the patient already has an appointment scheduled for the chosen
     * date.
     * 
     * @param data DTO containing the patient's ID and the requested appointment
     *             date.
     * @throws ValidationException if the patient already has a booking within the
     *                             clinic's operating hours today.
     */
    public void validate(DetailsConsultation data) {
        // Sets the time range for the clinic's operating hours on the requested day
        var firstTime = data.date().withHour(7);
        var lastTime = data.date().withHour(18);

        // Checks the database for any existing appointment for this patient between
        // 07:00 and 18:00
        var ThePatientAnotherAappointmentToday = consultationRepository
                .existsByPatientIdAndDateBetween(data.idPatient(), firstTime, lastTime);

        if (ThePatientAnotherAappointmentToday) {
            throw new ValidationException("Patient already has an appointment scheduled for this day.");
        }
    }

}