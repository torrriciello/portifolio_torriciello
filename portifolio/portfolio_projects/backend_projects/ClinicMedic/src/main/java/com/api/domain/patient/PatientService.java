package com.api.domain.patient;

import java.util.Objects;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.api.domain.ValidationException;

/**
 * Service layer for managing Patient business logic.
 * Handles CRUD operations and ensures data consistency before persistence.
 */
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Persists a new patient in the database.
     * 
     * @param patient The patient entity to be saved.
     * @return The saved patient with generated database ID.
     */
    @Transactional
    public Patient register(Patient patient) {
        // Defensive programming: ensures the provided entity is not null
        Objects.requireNonNull(patient, "Patient cannot be null");
        return patientRepository.save(patient);
    }

    /**
     * Retrieves a patient by their ID.
     * 
     * @param id The unique identifier of the patient.
     * @return The patient entity if found.
     * @throws ValidationException if the patient does not exist.
     */
    public Patient list(Long id) {
        // Validates that the provided ID is not null
        if (id == null) {
            throw new ValidationException("Patient ID cannot be null");
        }
        // Searches the database and throws an exception if the patient is not found
        return patientRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Patient not found"));
    }

    /**
     * Returns a paginated list of all patients with 'ativo' status set to true.
     * 
     * @param pageable Pagination and sorting criteria.
     * @return A page containing active patients.
     */
    public Page<Patient> findAllByAtivoTrue(Pageable pageable) {
        return patientRepository.findAllByAtivoTrue(pageable);
    }

    /**
     * Retrieves a lazy-loaded reference to a patient entity.
     * This avoids fetching the full entity when only the ID reference is needed.
     * 
     * @param id The patient ID.
     * @return A JPA proxy reference to the patient.
     */
    public Patient getReferenceById(Long id) {
        // Ensures the ID is valid before requesting a proxy reference
        if (id == null) {
            throw new ValidationException("Patient ID cannot be null");
        }
        return patientRepository.getReferenceById(id);
    }

    /**
     * Updates an existing patient's details.
     * Only allowed fields are mapped from the provided object.
     * 
     * @param patient The patient entity containing updated information.
     * @return The updated patient entity.
     */
    @Transactional
    public Patient update(Patient patient) {
        // Defensive check to ensure the provided entity is not null
        Objects.requireNonNull(patient, "Patient cannot be null");
        Long id = patient.getId();
        // Validates the ID before performing database operations
        if (id == null) {
            throw new ValidationException("Patient ID cannot be null");
        }
        // Retrieves the current patient state from the database
        Patient patientDB = patientRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Patient not found"));

        // Manual mapping of fields that are allowed to change
        patientDB.setName(patient.getName());
        patientDB.setEmail(patient.getEmail());
        patientDB.setPhone(patient.getPhone());
        patientDB.setAddress(patient.getAddress());

        return patientRepository.save(patientDB);
    }

    /**
     * Performs a logical deletion (Soft Delete) of a patient.
     * Instead of removing the record from the database, it marks it as inactive.
     * 
     * @param id The patient ID to be logically deleted.
     */
    @Transactional
    public void excluir(Long id) {
        // Validates that the ID is not null
        if (id == null) {
            throw new ValidationException("Patient ID cannot be null");
        }
        // Retrieves a proxy reference and performs the soft delete
        var patient = patientRepository.getReferenceById(id);
        patient.delete();
    }
}