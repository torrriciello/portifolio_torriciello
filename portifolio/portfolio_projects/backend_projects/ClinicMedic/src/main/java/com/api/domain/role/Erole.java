package com.api.domain.role;

/**
 * Enumeration representing the security roles (authorities) within the system.
 * These roles are used by Spring Security to authorize access to specific
 * endpoints.
 */
public enum Erole {

    ADMIN, // Full system access, managing doctors and patients
    DOCTOR, // Access to their own schedule and patient records
    PATIENT // Access to book appointments and view their own history
}