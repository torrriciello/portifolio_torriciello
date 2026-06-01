package com.api.domain.consultation;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (Record) used to return detailed information about a
 * consultation.
 * This record ensures that internal entity details are not exposed directly to
 * the API client.
 */
public record DataDetailsQuery(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {

    /**
     * Compact constructor that maps a Consultation entity to the DataDetailsQuery
     * DTO.
     * 
     * @param consultation The source entity from the database.
     */
    public DataDetailsQuery(Consultation consultation) {
        this(
                consultation.getId(),
                consultation.getDoctor().getId(),
                consultation.getPatient().getId(),
                consultation.getDate());
    }
}