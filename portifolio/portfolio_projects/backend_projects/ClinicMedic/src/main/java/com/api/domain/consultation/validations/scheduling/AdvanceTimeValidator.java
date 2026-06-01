package com.api.domain.consultation.validations.scheduling;

import org.springframework.stereotype.Component;
import com.api.domain.ValidationException;
import com.api.domain.consultation.DetailsConsultation;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Validator to ensure appointments are scheduled with a minimum lead time.
 * This prevents last-minute bookings that could disrupt the clinic's schedule.
 */
@Component("ValidatorScheduleAdvanceSchedule")
public class AdvanceTimeValidator implements AppointmentSchedulingValidator {

    /**
     * Validates if the appointment date is at least 30 minutes in the future from
     * now.
     * 
     * @param detailConsultation DTO containing the requested appointment date.
     * @throws ValidationException if the lead time is less than 30 minutes.
     */
    public void validate(DetailsConsultation detailConsultation) {
        var dateQuery = detailConsultation.date();
        var now = LocalDateTime.now();

        // Calculates the time gap in minutes between the current moment and the desired
        // appointment
        var differenceInMinutes = Duration.between(now, dateQuery).toMinutes();

        // Business Rule: Appointments must be made at least 30 minutes in advance
        if (differenceInMinutes < 30) {
            throw new ValidationException("Appointments must be scheduled at least 30 minutes in advance.");
        }
    }
}