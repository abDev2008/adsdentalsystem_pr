package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;
import com.abletocode.adsdentalsystem.dto.patient.UpdatePatientRequest;
import com.abletocode.adsdentalsystem.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody CreatePatientRequest request) {
        PatientResponse created = patientService.createPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping
    public ResponseEntity<List<PatientResponse>> getAll() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Patient> getById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @Valid @RequestBody UpdatePatientRequest request) {
        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
