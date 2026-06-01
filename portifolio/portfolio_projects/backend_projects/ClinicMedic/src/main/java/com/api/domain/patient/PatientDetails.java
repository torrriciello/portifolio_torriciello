package com.api.domain.patient;

import com.api.domain.address.Address;

/**
 * Data Transfer Object (Record) for returning patient details.
 * Provides a read-only representation of the Patient entity for API responses.
 */
public record PatientDetails(
        Long id,
        String name,
        String email,
        String cpf,
        String phone,
        Address addres) { // Note: 'addres' has a typo, consider 'address' for consistency

    /**
     * Mapping constructor to convert a Patient entity into a PatientDetails DTO.
     * 
     * @param patient The source entity retrieved from the database.
     */
    public PatientDetails(Patient patient) {
        this(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getCpf(),
                patient.getPhone(),
                patient.getAddress());
    }
}