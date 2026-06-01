package com.api.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.domain.patient.Patient;
import com.api.domain.patient.PatientDetails;
import com.api.domain.patient.PatientService;
import com.api.domain.patient.UpdatePatient;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Controller for managing patient-related operations.
 * All endpoints are secured with JWT authentication.
 */
@RequestMapping("/patients")
@RestController
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

    @Autowired
    private PatientService patientService;

    /**
     * Registers a new patient.
     * 
     * @param patient Entity containing patient information.
     * @return ResponseEntity with the created patient details and HTTP 200 OK.
     */
    @PostMapping("/register")
    public ResponseEntity<PatientDetails> register(@RequestBody @Valid Patient patient) {
        Patient newPatient = patientService.register(patient);
        return ResponseEntity.ok(new PatientDetails(newPatient));
    }

    /**
     * Retrieves details of a specific patient by their ID.
     * 
     * @param id The unique identifier of the patient.
     * @return ResponseEntity containing patient details.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientDetails> list(@PathVariable Long id) {
        Patient patient = patientService.list(id);
        return ResponseEntity.ok(new PatientDetails(patient));
    }

    /**
     * Lists active patients with pagination support.
     * 
     * @param pageable Pagination settings (default: 10 per page, sorted by name).
     * @return Paginated list of active patients.
     */
    @GetMapping
    public ResponseEntity<Page<PatientDetails>> listar(
            @PageableDefault(size = 10, sort = { "name" }) Pageable pageable) {
        var page = patientService.findAllByAtivoTrue(pageable)
                .map(PatientDetails::new);
        return ResponseEntity.ok(page);
    }

    /**
     * Updates an existing patient's record.
     * 
     * @param updatePatient DTO containing the ID and fields to be updated.
     * @return ResponseEntity with the updated information.
     */
    @PutMapping
    @Transactional // Maintains database integrity during the update process
    public ResponseEntity<PatientDetails> update(@RequestBody @Valid UpdatePatient updatePatient) {
        var patient = patientService.getReferenceById(updatePatient.id());
        patient.update(updatePatient);
        return ResponseEntity.ok(new PatientDetails(patient));
    }

    /**
     * Performs a logical deletion (deactivation) of a patient record.
     * 
     * @param id The unique identifier of the patient to be deactivated.
     * @return ResponseEntity with HTTP 204 No Content.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var patient = patientService.getReferenceById(id);
        patient.delete(); // Soft delete logic
        return ResponseEntity.noContent().build();
    }
}