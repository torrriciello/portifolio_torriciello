package com.api.domain.address;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (Record) for address information.
 * Uses Bean Validation to ensure required fields are not null or empty.
 */
public record DateAdress(
                @NotBlank(message = "CEP is required") String cep,

                @NotBlank(message = "Street/Public place is required") String publicPlace,

                @NotBlank(message = "House/Building number is required") String number,

                // Complement is optional, so no @NotBlank here
                String complement,

                @NotBlank(message = "Neighborhood is required") String neighborhood,

                @NotBlank(message = "Locality is required") String locality,

                @NotBlank(message = "State/UF is required") String uf,

                @NotBlank(message = "City is required") String city) {
}