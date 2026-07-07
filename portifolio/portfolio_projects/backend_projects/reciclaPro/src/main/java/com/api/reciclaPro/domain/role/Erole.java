package com.api.reciclaPro.domain.role;

/**
 * Enumeration representing the security roles (authorities) within the system.
 * These roles are used by Spring Security to authorize access to specific
 * endpoints.
 */
public enum Erole {

    ADMIN, 
    COLLECTOR, 
    TAXPAYER 
}
