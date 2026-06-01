package com.api.domain.consultation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.api.domain.doctor.Doctor;
import com.api.domain.patient.Patient;

/**
 * Entity representing a medical consultation record in the database.
 */
@Table(name = "consultations")
@Entity(name = "Consultation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") // Best practice: only use ID for equality to avoid performance issues
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // LAZY fetch prevents the application from loading Doctor/Patient data unless
    // explicitly accessed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime date;

    // Maps the Enum to a String in the database for better readability in SQL
    // queries
    @Column(name = "reason_cancellation")
    @Enumerated(EnumType.STRING)
    private ReasonCancellation reasonCancellation;

    /**
     * Logic to cancel a consultation by setting a reason.
     * 
     * @param reason The Enum value representing why the appointment was cancelled.
     */
    public void cancel(ReasonCancellation reason) {
        this.reasonCancellation = reason;
    }

}