package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Surgery;
import com.abletocode.adsdentalsystem.dto.surgery.CreateSurgeryRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.SurgeryMapper;
import com.abletocode.adsdentalsystem.repository.SurgeryRepository;
import com.abletocode.adsdentalsystem.service.SurgeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SurgeryServiceImpl implements SurgeryService {

    private final SurgeryRepository surgeryRepository;

    @Override
    public Surgery createSurgery(CreateSurgeryRequest request) {
        return surgeryRepository.save(SurgeryMapper.toEntity(request));
    }

    @Override
    public List<Surgery> getAllSurgeries() {
        return surgeryRepository.findAll();
    }

    @Override
    public Surgery getSurgeryById(Long id) {
        return surgeryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Surgery not found"));
    }



}
