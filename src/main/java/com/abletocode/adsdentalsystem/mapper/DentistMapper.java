package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;

public class DentistMapper {
    public static Dentist toEntity(CreateDentistRequest dto) {
        Dentist d = new Dentist();
        d.setEmail(dto.getEmail());
        d.setPhone(dto.getPhone());
        d.setSpecialization(dto.getSpecialization());
        d.setProfilePictureUrl(dto.getProfilePictureUrl());
        return d;
    }
}
