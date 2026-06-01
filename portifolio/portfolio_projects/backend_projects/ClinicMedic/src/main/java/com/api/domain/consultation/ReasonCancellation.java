package com.api.domain.consultation;

/**
 * Enumeration representing the possible reasons for cancelling a consultation.
 */
public enum ReasonCancellation {

    PATIENT_WITHDREW, // Paciente desistiu
    DOCTOR_CANCELLED, // Médico cancelou
    OTHER; // Outros
}