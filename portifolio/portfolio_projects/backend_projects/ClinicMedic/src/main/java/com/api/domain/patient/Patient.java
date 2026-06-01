package com.api.domain.patient;

import com.api.domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Patient in the clinic's system.
 * Implements soft delete functionality via the 'ativo' attribute to preserve
 * medical history.
 */
@Table(name = "patients")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String cpf;

    @Embedded
    private Address address;

    private Boolean ativo; // Status flag: true for active, false for inactive/deleted

    /**
     * Constructor to initialize a Patient from registration data.
     * Sets default status to active.
     */
    public Patient(RegisterPatient registerPatient) {
        this.ativo = true;
        this.name = registerPatient.name();
        this.email = registerPatient.email();
        this.phone = registerPatient.phone();
        this.cpf = registerPatient.cpf();
        // Ensure the address is initialized to avoid NullPointerException during
        // updates
        this.address = new Address();
    }

    /**
     * Updates patient personal details and address if new data is provided.
     * 
     * @param updatePatient DTO with the fields to be updated.
     */
    public void update(UpdatePatient updatePatient) {
        if (updatePatient.name() != null) {
            this.name = updatePatient.name();
        }
        if (updatePatient.telefone() != null) {
            this.phone = updatePatient.telefone();
        }
        if (updatePatient.address() != null) {
            this.address.updateAdress(updatePatient.address());
        }
    }

    /**
     * Performs a logical deletion by deactivating the patient record.
     */
    public void delete() {
        this.ativo = false;
    }

}