package com.api.infra.security;

/**
 * Data Transfer Object (Record) for the JWT response.
 * This is the final payload sent to the client after successful authentication.
 */
public record TokenJWTData(String token) {
}