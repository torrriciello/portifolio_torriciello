package com.api.domain.consultation.validations.scheduling;

import org.springframework.stereotype.Component;
import com.api.domain.ValidationException;
import com.api.domain.consultation.DetailsConsultation;
import java.time.DayOfWeek;

/**
 * Validator to ensure the appointment date and time fall within the clinic's
 * operating hours.
 * Operating hours: Monday to Saturday, from 07:00 to 19:00.
 */
@Component
public class ValidatorOperatingHoursClinic implements AppointmentSchedulingValidator {

    /**
     * Validates if the requested consultation date is on a Sunday or outside
     * business hours.
     * 
     * @param data DTO containing the proposed appointment date and time.
     * @throws ValidationException if the clinic is closed at the requested time.
     */
    public void validate(DetailsConsultation data) {
        var dataConsulta = data.date();

        // Checks if the day is Sunday
        var sunday = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);

        // Checks if the time is before 07:00 AM
        var beforeTheClinicOpening = dataConsulta.getHour() < 7;

        // Checks if the time is after 06:00 PM (18:00)
        // Note: This means the last valid hour for an appointment start is 18:xx
        var afterTheClinicClosure = dataConsulta.getHour() > 18;

        if (sunday || beforeTheClinicOpening || afterTheClinicClosure) {
            throw new ValidationException("Consultation is outside the clinic's operating hours.");
        }
    }
}