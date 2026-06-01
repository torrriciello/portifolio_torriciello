package com.api.infra.exception;

import com.api.domain.ValidationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Global exception handler that catches and formats application-wide errors.
 * Translates Java exceptions into meaningful RESTful HTTP responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles 404 Not Found errors when an entity is not located in the database.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    /**
     * Handles 400 Bad Request errors specifically for Bean Validation failures.
     * Extracts field names and error messages into a clean JSON list.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataErrorValidation>> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        var listaErros = erros.stream()
                .map(DataErrorValidation::new)
                .toList();
        return ResponseEntity.badRequest().body(listaErros);
    }

    /**
     * Handles custom business logic validation errors.
     * Returns a 400 Bad Request with the specific validation message.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> tratarErroRegraDeNegocio(ValidationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Internal DTO (Record) to represent field-level validation errors.
     */
    private record DataErrorValidation(String field, String message) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}