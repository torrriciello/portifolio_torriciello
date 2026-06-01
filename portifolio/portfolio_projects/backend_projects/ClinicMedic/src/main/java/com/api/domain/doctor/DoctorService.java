package com.api.domain.doctor;

import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing Doctor business logic.
 * Acts as an intermediary between the Web layer and the Data Access layer.
 */
@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    /**
     * Registers a new doctor in the database.
     * 
     * @param doctor The doctor entity to be persisted.
     * @return The saved doctor entity with its generated ID.
     */
    public Doctor register(Doctor doctor) {
        // Defensive programming to ensure the entity is not null
        Objects.requireNonNull(doctor, "Doctor cannot be null");
        return doctorRepository.save(doctor);
    }

    /**
     * Retrieves a proxy reference to a Doctor entity.
     * Useful for performance when you only need the ID for relationship mapping.
     * 
     * @param id The doctor's ID.
     * @return A JPA entity reference (lazy loaded).
     */
    public Doctor getReferenceById(Long id) {

        if (id == null) {
            throw new IllegalArgumentException("Doctor ID cannot be null");
        }
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    /**
     * Lists all active doctors with pagination support.
     * 
     * @param pageable Pagination and sorting criteria.
     * @return A page of active doctors.
     */
    public Page<Doctor> findAllByAtivoTrue(Pageable pageable) {
        return doctorRepository.findAllByAtivoTrue(pageable);
    }

}