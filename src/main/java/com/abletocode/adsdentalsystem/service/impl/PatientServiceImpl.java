package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;
import com.abletocode.adsdentalsystem.mapper.PatientMapper;
import com.abletocode.adsdentalsystem.repository.PatientRepository;
import com.abletocode.adsdentalsystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public PatientResponse createPatient(CreatePatientRequest request) {
        Patient patient = PatientMapper.toEntity(request);
        patient = patientRepository.save(patient);
        return PatientMapper.toResponse(patient);
    }
}
