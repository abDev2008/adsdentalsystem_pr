package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.domain.Availability;
import com.abletocode.adsdentalsystem.dto.availability.CreateAvailabilityRequest;
import com.abletocode.adsdentalsystem.dto.availability.UpdateAvailabilityRequest;

import java.util.List;

public interface AvailabilityService {
    Availability createAvailability(CreateAvailabilityRequest request);
    List<Availability> getAllAvailabilities();
    Availability getAvailabilityById(Long id);
    Availability updateAvailability(Long id, UpdateAvailabilityRequest request);
    void deleteAvailability(Long id);
}
