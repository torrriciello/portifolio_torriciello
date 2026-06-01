package com.api.domain;

/**
 * Custom exception for business logic validation errors.
 * Used to differentiate functional validation failures from generic system
 * errors.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructs a new ValidationException with the specified detail message.
     * 
     * @param message The specific business rule that was violated.
     */
    public ValidationException(String message) {
        super(message);
    }
}