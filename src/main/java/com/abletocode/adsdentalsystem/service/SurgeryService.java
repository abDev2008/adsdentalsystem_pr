package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.domain.Surgery;
import com.abletocode.adsdentalsystem.dto.surgery.CreateSurgeryRequest;

public interface SurgeryService {
    Surgery createSurgery(CreateSurgeryRequest request);
}
