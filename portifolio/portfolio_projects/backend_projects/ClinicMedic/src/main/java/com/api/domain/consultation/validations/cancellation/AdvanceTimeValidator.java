package com.api.domain.consultation.validations.cancellation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.api.domain.ValidationException;
import com.api.domain.consultation.ConsultationRepository;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Validator to ensure that a consultation is canceled at least 24 hours in
 * advance.
 */
@Component("ValidadorHorarioAntecedenciaCancelamento")
public class AdvanceTimeValidator implements ConsultationCancellationValidator {

    @Autowired
    private ConsultationRepository repository;

    /**
     * Validates the cancellation time rule.
     * 
     * @param data DTO containing the consultation ID.
     * @throws ValidationException if the cancellation is requested less than 24
     *                             hours before the appointment.
     */
    @Override
    public void validate(CancellationDataConsultation data) {

        Long id = data.idConsultation();

        if (id == null) {
            throw new ValidationException("Consultation ID cannot be null");
        }

        var consultation = repository.getReferenceById(id);

        var now = LocalDateTime.now();

        var differenceInHours = Duration.between(now, consultation.getDate()).toHours();

        if (differenceInHours < 24) {
            throw new ValidationException("Consultations can only be canceled with at least 24 hours notice!");
        }
    }
}