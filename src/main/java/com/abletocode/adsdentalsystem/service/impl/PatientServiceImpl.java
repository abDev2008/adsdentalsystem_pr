package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;
import com.abletocode.adsdentalsystem.dto.patient.UpdatePatientRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.PatientMapper;
import com.abletocode.adsdentalsystem.repository.PatientRepository;
import com.abletocode.adsdentalsystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientResponse createPatient(CreatePatientRequest request) {
        Patient patient = patientMapper.toEntity(request);
        patient = patientRepository.save(patient);
        return patientMapper.toResponse(patient);
    }

    @Override
    public Page<PatientResponse> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable)
                .map(patientMapper::toResponse);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    @Override
    public Patient updatePatient(Long id, UpdatePatientRequest request) {
        Patient existing = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));

        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        existing.setPhone(request.getPhone());
        existing.setDob(request.getDob());
        existing.setAddress(request.getAddress());
        existing.setProfilePictureUrl(request.getProfilePictureUrl());

        return patientRepository.save(existing);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
        patientRepository.delete(patient);
    }
}
