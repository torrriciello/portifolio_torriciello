package com.api.domain.consultation;

import java.time.LocalDateTime;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Consultation entity operations.
 * Uses Spring Data JPA to generate SQL queries automatically from method names.
 */
@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    /**
     * Checks if a patient already has an appointment within a specific time range.
     * Used to prevent multiple bookings on the same day.
     * 
     * @param idPatient     The patient's unique ID.
     * @param firstSchedule Start of the time range (e.g., 07:00 AM).
     * @param lastTime      End of the time range (e.g., 06:00 PM).
     * @return true if a consultation exists, false otherwise.
     */
    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstSchedule, LocalDateTime lastTime);

    /**
     * Checks if a doctor has an active appointment at a specific date and time.
     * "Active" means the cancellation reason is null.
     * 
     * @param idDoctor The doctor's unique ID.
     * @param date     The specific date and time to check.
     * @return true if the doctor is already booked, false otherwise.
     */
    boolean existsByDoctorIdAndDateAndReasonCancellationIsNull(Long idDoctor, LocalDateTime date);

    /**
     * Retrieves a paginated list of all active (non-cancelled) appointments for a
     * specific doctor.
     * 
     * @param doctorId The doctor's unique ID.
     * @param pageable Pagination and sorting information.
     * @return A page of active consultations.
     */
    Page<Consultation> findAllByDoctorIdAndReasonCancellationIsNull(Long doctorId, Pageable pageable);
}