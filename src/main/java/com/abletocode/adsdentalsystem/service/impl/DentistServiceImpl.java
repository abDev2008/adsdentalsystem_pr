package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.mapper.DentistMapper;
import com.abletocode.adsdentalsystem.repository.DentistRepository;
import com.abletocode.adsdentalsystem.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;

    @Override
    public Dentist createDentist(CreateDentistRequest request) {
        Dentist dentist = DentistMapper.toEntity(request);
        return dentistRepository.save(dentist);
    }
}
