package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;
import com.abletocode.adsdentalsystem.dto.patient.UpdatePatientRequest;

import java.util.List;

public interface PatientService {
    PatientResponse createPatient(CreatePatientRequest request);
    Patient getPatientById(Long id);
    Patient updatePatient(Long id, UpdatePatientRequest request);
    void deletePatient(Long id);
    List<PatientResponse> getAllPatients();

}
