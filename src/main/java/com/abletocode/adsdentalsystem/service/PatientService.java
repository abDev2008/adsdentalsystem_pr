package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;

public interface PatientService {
    PatientResponse createPatient(CreatePatientRequest request);
}
