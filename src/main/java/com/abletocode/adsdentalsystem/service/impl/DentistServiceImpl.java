package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.dto.dentist.DentistResponse;
import com.abletocode.adsdentalsystem.dto.dentist.UpdateDentistRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.DentistMapper;
import com.abletocode.adsdentalsystem.repository.DentistRepository;
import com.abletocode.adsdentalsystem.service.DentistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DentistServiceImpl implements DentistService {

    private final DentistRepository dentistRepository;
    private final DentistMapper dentistMapper;

    @Override
    public Dentist createDentist(CreateDentistRequest request) {
        Dentist dentist = dentistMapper.toEntity(request);
        return dentistRepository.save(dentist);
    }

    @Override
    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    @Override
    public Dentist getDentistById(Long id) {
        return dentistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found"));
    }

    @Override
    public DentistResponse updateDentist(Long id, UpdateDentistRequest request) {
        Dentist dentist = dentistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id: " + id));

        dentist.setFirstName(request.getFirstName());
        dentist.setLastName(request.getLastName());
        dentist.setPhone(request.getPhone());
        dentist.setSpecialization(request.getSpecialization());
        dentist.setExperienceYears(request.getExperienceYears());

        Dentist updatedDentist = dentistRepository.save(dentist);

        return dentistMapper.toResponse(updatedDentist);
    }

    @Override
    public void deleteDentist(Long id) {
        Dentist dentist = dentistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id: " + id));
        dentistRepository.delete(dentist);
    }

}
