package com.api.domain.consultation.validations.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.api.domain.ValidationException;
import com.api.domain.consultation.DetailsConsultation;
import com.api.domain.doctor.DoctorRepository;

/**
 * Validator to check if the selected doctor is active in the system.
 * Prevents appointments from being scheduled with deactivated (soft-deleted)
 * doctors.
 */
@Component
public class ActiveMedicalValidator implements AppointmentSchedulingValidator {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Validates if the doctor is currently active.
     * 
     * @param detailConsultation DTO containing the appointment request details.
     * @throws ValidationException if the doctor is found but is marked as inactive.
     */
    public void validate(DetailsConsultation detailConsultation) {
        // If no specific doctor is selected (automatic assignment), validation is
        // skipped here
        if (detailConsultation.idDoctor() == null) {
            return;
        }

        // Checks the 'ativo' (active) status in the database for the given doctor ID
        var doctorIsActive = doctorRepository.findAtivoById(detailConsultation.idDoctor());

        if (!doctorIsActive) {
            throw new ValidationException("Appointment cannot be scheduled with an inactive/deleted doctor.");
        }
    }

}