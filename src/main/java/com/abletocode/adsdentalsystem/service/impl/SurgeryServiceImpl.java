package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Surgery;
import com.abletocode.adsdentalsystem.dto.surgery.CreateSurgeryRequest;
import com.abletocode.adsdentalsystem.mapper.SurgeryMapper;
import com.abletocode.adsdentalsystem.repository.SurgeryRepository;
import com.abletocode.adsdentalsystem.service.SurgeryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurgeryServiceImpl implements SurgeryService {

    private final SurgeryRepository surgeryRepository;

    @Override
    public Surgery createSurgery(CreateSurgeryRequest request) {
        return surgeryRepository.save(SurgeryMapper.toEntity(request));
    }
}
