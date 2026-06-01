package com.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Represents a physical address as an embeddable component for other entities.
 * In the database, these fields will be columns within the owner's table.
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String cep;
    private String publicPlace; // Street or Avenue
    private String number;
    private String complement;
    private String neighborhood;
    private String locality;
    private String uf; // State initials (e.g., SP, RJ)
    private String city;

    /**
     * Constructor to initialize address from a Data Transfer Object (DTO).
     * 
     * @param dateAdress DTO containing initial address data.
     */
    public Address(DateAdress dateAdress) {
        this.publicPlace = dateAdress.publicPlace();
        this.neighborhood = dateAdress.neighborhood();
        this.cep = dateAdress.cep();
        this.uf = dateAdress.uf();
        this.locality = dateAdress.locality();
        this.city = dateAdress.city();
        this.number = dateAdress.number();
        this.complement = dateAdress.complement();
    }

    /**
     * Updates address fields only if the new values are not null.
     * This follows the partial update pattern.
     * 
     * @param address DTO containing the fields to be updated.
     */
    public void updateAdress(DateAdress address) {

        if (address.publicPlace() != null) {
            this.publicPlace = address.publicPlace();
        }

        if (address.neighborhood() != null) {
            this.neighborhood = address.neighborhood();
        }

        if (address.cep() != null) {
            this.cep = address.cep();
        }

        if (address.uf() != null) {
            this.uf = address.uf();
        }

        if (address.locality() != null) {
            this.locality = address.locality();
        }

        if (address.city() != null) {
            this.city = address.city();
        }

        if (address.number() != null) {
            this.number = address.number();
        }

        if (address.complement() != null) {
            this.complement = address.complement();
        }
    }
}