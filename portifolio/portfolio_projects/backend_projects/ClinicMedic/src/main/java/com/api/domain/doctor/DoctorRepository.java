package com.api.domain.doctor;

import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Doctor entity operations.
 * Includes complex JPQL queries for medical availability and scheduling logic.
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    /**
     * Retrieves all doctors who are not soft-deleted, with pagination support.
     */
    Page<Doctor> findAllByAtivoTrue(Pageable pageable);

    /**
     * Finds a random available doctor for a specific specialty and time.
     * Logic:
     * 1. Doctor must be active.
     * 2. Doctor must have the required specialty.
     * 3. Doctor must NOT have any active (non-cancelled) appointment at that time.
     * * @param especialidade The required medical specialty.
     * 
     * @param data The requested appointment date and time.
     * @return An available doctor or null if none are found.
     */
    @Query("""
            select m from Doctor m
            where
            m.ativo = true
            and
            m.especialidade = :especialidade
            and
            m.id not in(
                select c.doctor.id from Consultation c
                where
                c.date = :data
                and
                c.reasonCancellation is null
            )
            order by function('random')
            limit 1
            """)
    Doctor chooseRandomFreeDoctorDate(Specialty especialidade, LocalDateTime data);

    /**
     * Checks only the 'ativo' status of a doctor by their ID.
     * Optimized to return only a Boolean instead of the full entity.
     */
    @Query("""
            select m.ativo
            from Doctor m
            where
            m.id = :id
            """)
    Boolean findAtivoById(Long id);
}