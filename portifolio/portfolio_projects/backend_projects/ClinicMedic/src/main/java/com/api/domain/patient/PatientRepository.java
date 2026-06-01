package com.api.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for Patient entity operations.
 * Leverages Spring Data JPA to handle database interactions and custom JPQL
 * queries.
 */
public interface PatientRepository extends JpaRepository<Patient, Long> {

    /**
     * Retrieves a paginated list of all active patients.
     * 
     * @param pageable Pagination and sorting information.
     * @return A Page of active patients.
     */
    Page<Patient> findAllByAtivoTrue(Pageable pageable);

    /**
     * Optimized query to check the 'ativo' status of a patient without loading the
     * full entity.
     * Useful for validation rules before scheduling an appointment.
     * 
     * @param id The patient's unique ID.
     * @return Boolean indicating if the patient is active.
     */
    @Query("""
            select p.ativo
            from Patient p
            where p.id = :id
            """)
    Boolean findAtivoById(@Param("id") Long id);
}