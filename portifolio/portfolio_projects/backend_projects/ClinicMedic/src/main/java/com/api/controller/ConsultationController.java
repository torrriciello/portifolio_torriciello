package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.api.domain.consultation.AppointmentSchedule;
import com.api.domain.consultation.DataDetailsQuery;
import com.api.domain.consultation.DetailsConsultation;
import com.api.domain.consultation.validations.cancellation.CancellationDataConsultation;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Controller for managing medical consultations/appointments.
 * Requires a Bearer Token for all operations.
 */
@RequestMapping("consultation")
@RestController
@SecurityRequirement(name = "bearer-key") // Swagger documentation for JWT security
public class ConsultationController {

    @Autowired
    private AppointmentSchedule schedule;

    /**
     * Schedules a new consultation.
     * * @param data DTO containing patient, doctor, and date information.
     * 
     * @return ResponseEntity with the details of the created appointment.
     */
    @PostMapping
    @Transactional // Ensures database consistency for the entire scheduling flow
    public ResponseEntity<DataDetailsQuery> schedule(@RequestBody @Valid DetailsConsultation data) {
        // Delegates business logic and validations to the service layer
        var dto = schedule.schedule(data);

        // Returns the created appointment details with HTTP 200 OK
        return ResponseEntity.ok(dto);
    }

    /**
     * Cancels an existing consultation.
     * * @param data DTO containing the appointment ID and the cancellation reason.
     * 
     * @return ResponseEntity with HTTP 204 No Content upon success.
     */
    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid CancellationDataConsultation data) {
        // Triggers the cancellation process and business rules
        schedule.cancel(data);

        // Returns 204 No Content as per REST standards for successful deletions
        return ResponseEntity.noContent().build();
    }
}