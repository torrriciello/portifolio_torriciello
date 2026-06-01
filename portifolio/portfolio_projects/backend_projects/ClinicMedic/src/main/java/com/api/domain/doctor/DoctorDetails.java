package com.api.domain.doctor;

import com.api.domain.address.Address;

/**
 * Data Transfer Object (Record) for returning full doctor details.
 * Used primarily in GET by ID or as a response after a successful POST/PUT
 * operation.
 */
public record DoctorDetails(
        Long id,
        String name,
        String phone,
        Specialty especialidade,
        String cpf,
        String crm,
        Address address) {

    /**
     * Secondary constructor to map a Doctor entity to this DTO.
     * This keeps mapping logic centralized within the Record.
     * 
     * @param doctor The source Doctor entity.
     */
    public DoctorDetails(Doctor doctor) {
        this(
                doctor.getId(),
                doctor.getName(),
                doctor.getPhone(),
                doctor.getEspecialidade(),
                doctor.getCpf(),
                doctor.getCrm(),
                doctor.getAddress());
    }
}