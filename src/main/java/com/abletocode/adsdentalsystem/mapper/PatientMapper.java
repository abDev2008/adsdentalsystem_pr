package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;

public class PatientMapper {

    public static Patient toEntity(CreatePatientRequest dto) {
        Patient p = new Patient();
        p.setFirstName(dto.getFirstName());
        p.setLastName(dto.getLastName());
        p.setEmail(dto.getEmail());
        p.setPhone(dto.getPhone());
        p.setAddress(dto.getAddress());
        p.setDob(dto.getDob());
        return p;
    }

    public static PatientResponse toResponse(Patient p) {
        return new PatientResponse(
                p.getId(),
                p.getFirstName() + " " + p.getLastName(),
                p.getEmail(),
                p.getPhone(),
                p.getAddress(),
                p.getDob()
        );
    }
}
