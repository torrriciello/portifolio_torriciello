package com.api.domain.consultation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.domain.ValidationException;
import com.api.domain.consultation.validations.cancellation.CancellationDataConsultation;
import com.api.domain.consultation.validations.cancellation.ConsultationCancellationValidator;
import com.api.domain.consultation.validations.scheduling.AppointmentSchedulingValidator;
import com.api.domain.doctor.Doctor;
import com.api.domain.doctor.DoctorRepository;
import com.api.domain.patient.PatientRepository;

import java.util.List;

/**
 * Service class responsible for the business logic of scheduling and canceling
 * appointments.
 * It coordinates validators and repositories to ensure data integrity.
 */
@Service
public class AppointmentSchedule {

    @Autowired
    private ConsultationRepository consultationRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // Spring automatically injects all implementations of these interfaces into
    // these lists
    @Autowired
    private List<AppointmentSchedulingValidator> validators;

    @Autowired
    private List<ConsultationCancellationValidator> validatorsCancellation;

    /**
     * Orchestrates the scheduling of a new consultation.
     * 
     * @param data DTO containing the desired appointment details.
     * @return DataDetailsQuery with the confirmed appointment information.
     */
    public DataDetailsQuery schedule(DetailsConsultation data) {

        Long patientId = data.idPatient();
        Long doctorId = data.idDoctor();

        if (patientId == null || !patientRepository.existsById(patientId)) {
            throw new ValidationException("The provided Patient ID does not exist!");
        }

        if (doctorId != null && !doctorRepository.existsById(doctorId)) {
            throw new ValidationException("The provided Doctor ID does not exist!");
        }

        validators.forEach(v -> v.validate(data));

        var patient = patientRepository.getReferenceById(patientId);
        var doctor = chooseDoctor(data);

        if (doctor == null) {
            throw new ValidationException("No doctor is available for the selected date and specialty!");
        }

        var consultation = new Consultation(null, doctor, patient, data.date(), null);
        consultationRepository.save(consultation);

        return new DataDetailsQuery(consultation);
    }

    /**
     * Orchestrates the cancellation of an existing consultation.
     * 
     * @param data DTO containing consultation ID and cancellation reason.
     */
    public void cancel(CancellationDataConsultation data) {

        Long consultationId = data.idConsultation();

        if (consultationId == null || !consultationRepository.existsById(consultationId)) {
            throw new ValidationException("The provided Consultation ID does not exist!");
        }

        validatorsCancellation.forEach(v -> v.validate(data));

        var consultation = consultationRepository.getReferenceById(consultationId);
        consultation.cancel(data.reasonCancellation());
    }

    /**
     * Logic to select a doctor: either the requested one or a random available one
     * by specialty.
     * 
     * @param data Consultation details.
     * @return A Doctor entity or null if none are available.
     */
    private Doctor chooseDoctor(DetailsConsultation data) {

        Long doctorId = data.idDoctor();

        if (doctorId != null) {
            return doctorRepository.getReferenceById(doctorId);
        }

        if (data.specialty() == null) {
            throw new ValidationException("Specialty is required when a specific doctor is not chosen!");
        }

        return doctorRepository.chooseRandomFreeDoctorDate(data.specialty(), data.date());
    }
}