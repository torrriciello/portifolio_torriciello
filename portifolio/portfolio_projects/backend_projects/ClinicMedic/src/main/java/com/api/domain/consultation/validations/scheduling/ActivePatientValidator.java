package com.api.domain.consultation.validations.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.api.domain.ValidationException;
import com.api.domain.consultation.DetailsConsultation;
import com.api.domain.patient.PatientRepository;

/**
 * Validator to ensure that the patient is active in the system before
 * scheduling.
 * Prevents scheduling appointments for patients who have been soft-deleted or
 * deactivated.
 */
@Component
public class ActivePatientValidator implements AppointmentSchedulingValidator {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Validates the patient's active status.
     * 
     * @param data DTO containing the appointment request details, including patient
     *             ID.
     * @throws ValidationException if the patient is found to be inactive/deleted.
     */
    public void validate(DetailsConsultation data) {
        // Checks the 'ativo' (active) status in the database for the given patient ID
        var patientIsActive = patientRepository.findAtivoById(data.idPatient());

        if (!patientIsActive) {
            throw new ValidationException("Appointment cannot be scheduled with an inactive/deleted patient.");
        }
    }
}