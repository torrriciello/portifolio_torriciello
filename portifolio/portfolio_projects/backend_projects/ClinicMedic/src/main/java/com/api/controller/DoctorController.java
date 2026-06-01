package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.api.domain.doctor.Doctor;
import com.api.domain.doctor.DoctorDetails;
import com.api.domain.doctor.DoctorService;
import com.api.domain.doctor.RegisterDoctor;
import com.api.domain.doctor.UpdateDoctor;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 * Controller responsible for managing Doctor records.
 * Secured by JWT (bearer-key).
 */
@RequestMapping("/doctor")
@RestController
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    /**
     * Registers a new doctor in the system.
     * 
     * @param data Validated DTO containing registration details.
     * @return ResponseEntity with the created doctor's details.
     */
    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DoctorDetails> register(@RequestBody @Valid RegisterDoctor data) {
        var doctor = new Doctor(data);
        doctorService.register(doctor);
        return ResponseEntity.ok(new DoctorDetails(doctor));
    }

    /**
     * Lists all active doctors with pagination.
     * Default: 10 elements per page, sorted by name.
     * 
     * @param pageable Pagination parameters (page, size, sort).
     * @return A paginated list of DoctorDetails.
     */
    @GetMapping
    public Page<DoctorDetails> list(@PageableDefault(size = 10, sort = { "name" }) Pageable pageable) {
        // Only returns doctors where 'ativo' (active) is true
        return doctorService.findAllByAtivoTrue(pageable).map(DoctorDetails::new);
    }

    /**
     * Updates an existing doctor's information.
     * 
     * @param updateDoctor DTO containing the ID and the fields to be updated.
     */
    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDoctor updateDoctor) {
        // Fetches a reference (proxy) of the entity and updates its state
        var doctor = doctorService.getReferenceById(updateDoctor.id());
        doctor.update(updateDoctor);
    }

    /**
     * Performs a logical delete (soft delete) of a doctor.
     * Instead of removing the record from the database, it marks it as inactive.
     * 
     * @param id Unique identifier of the doctor.
     */
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var doctor = doctorService.getReferenceById(id);
        doctor.delete(); // Sets 'ativo' property to false
    }
}