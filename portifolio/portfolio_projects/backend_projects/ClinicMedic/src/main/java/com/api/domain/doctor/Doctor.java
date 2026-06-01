package com.api.domain.doctor;

import com.api.domain.address.Address;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity representing a Doctor in the system.
 * Uses logical deletion (soft delete) via the 'ativo' field.
 */
@Entity
@Table(name = "doctors")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Valid
    private Specialty especialidade;

    private String name;

    private String cpf;

    private String crm; // Medical License Number

    private String phone;

    private Boolean ativo; // Logical delete flag (true = active, false = deleted)

    @Embedded
    @NotNull(message = "Address is mandatory")
    @Valid
    private Address address;

    /**
     * Constructor to create a new Doctor from registration data.
     * Automatically sets the status as active.
     */
    public Doctor(RegisterDoctor registerDoctor) {
        this.ativo = true;
        this.name = registerDoctor.name();
        this.phone = registerDoctor.phone();
        this.crm = registerDoctor.crm();
        this.cpf = registerDoctor.cpf();
        this.especialidade = registerDoctor.especialidade();
        this.address = registerDoctor.address();
    }

    /**
     * Updates doctor information based on non-null fields in the DTO.
     * 
     * @param updateDoctor DTO containing updateable fields.
     */
    public void update(UpdateDoctor updateDoctor) {
        if (updateDoctor.name() != null) {
            this.name = updateDoctor.name();
        }
        if (updateDoctor.phone() != null) {
            this.phone = updateDoctor.phone();
        }
        // Delegates the address update to the Address embeddable object
        if (updateDoctor.address() != null) {
            this.address.updateAdress(updateDoctor.address());
        }
    }

    /**
     * Performs a soft delete by setting the 'ativo' flag to false.
     * This preserves historical data in the database while hiding the record from
     * the UI.
     */
    public void delete() {
        this.ativo = false;
    }

}