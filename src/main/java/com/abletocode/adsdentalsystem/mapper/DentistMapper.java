package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.dto.dentist.DentistResponse;
import org.springframework.stereotype.Component;

@Component
public class DentistMapper {

    public Dentist toEntity(CreateDentistRequest dto) {
        Dentist d = new Dentist();
        d.setEmail(dto.getEmail());
        d.setPhone(dto.getPhone());
        d.setSpecialization(dto.getSpecialization());
        d.setProfilePictureUrl(dto.getProfilePictureUrl());
        d.setFirstName(dto.getFirstName());
        d.setLastName(dto.getLastName());
        d.setExperienceYears(dto.getExperienceYears());
        return d;
    }

    public DentistResponse toResponse(Dentist entity) {
        DentistResponse response = new DentistResponse();
        response.setId(entity.getId());
        response.setEmail(entity.getEmail());
        response.setPhone(entity.getPhone());
        response.setSpecialization(entity.getSpecialization());
        response.setProfilePictureUrl(entity.getProfilePictureUrl());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setExperienceYears(entity.getExperienceYears());
        return response;
    }
}
