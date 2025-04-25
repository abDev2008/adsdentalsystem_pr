package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper {

    public Patient toEntity(CreatePatientRequest dto) {
        Patient patient = new Patient();
        patient.setEmail(dto.getEmail());
        patient.setPhone(dto.getPhone());
        patient.setFirstName(dto.getFirstName());
        patient.setLastName(dto.getLastName());
        patient.setAddress(dto.getAddress());
        patient.setDob(dto.getDob());
        patient.setProfilePictureUrl(dto.getProfilePictureUrl());
        return patient;
    }

    public PatientResponse toResponse(Patient entity) {
        PatientResponse response = new PatientResponse();
        response.setId(entity.getId());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setAddress(entity.getAddress());
        response.setDob(entity.getDob());
        response.setFullName(entity.getFirstName() + " " + entity.getLastName());
        return response;
    }
}
