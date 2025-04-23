package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.domain.Surgery;
import com.abletocode.adsdentalsystem.dto.surgery.CreateSurgeryRequest;

import java.util.List;

public interface SurgeryService {
    Surgery createSurgery(CreateSurgeryRequest request);
    List<Surgery> getAllSurgeries();
    Surgery getSurgeryById(Long id);

}
