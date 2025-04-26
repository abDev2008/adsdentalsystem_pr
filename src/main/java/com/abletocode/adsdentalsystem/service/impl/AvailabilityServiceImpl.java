package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Availability;
import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.availability.CreateAvailabilityRequest;
import com.abletocode.adsdentalsystem.dto.availability.UpdateAvailabilityRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.AvailabilityMapper;
import com.abletocode.adsdentalsystem.repository.AvailabilityRepository;
import com.abletocode.adsdentalsystem.repository.DentistRepository;
import com.abletocode.adsdentalsystem.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final DentistRepository dentistRepository;
    private final AvailabilityMapper availabilityMapper;

    @Override
    public Availability createAvailability(CreateAvailabilityRequest request) {
        Dentist dentist = dentistRepository.findById(request.getDentistId())
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found"));

        Availability newAvailability = availabilityMapper.toEntity(request, dentist);

        // Calculate day of week from start time
        DayOfWeek dayOfWeek = request.getStartTime().getDayOfWeek();

        // Check for overlapping availabilities on the same day
        List<Availability> existing = availabilityRepository.findByDentistIdAndDayOfWeek(dentist.getId(), dayOfWeek);

        for (Availability existingSlot : existing) {
            boolean overlaps =
                    !newAvailability.getEndTime().isBefore(existingSlot.getStartTime()) &&
                            !newAvailability.getStartTime().isAfter(existingSlot.getEndTime());

            if (overlaps) {
                throw new IllegalArgumentException("Overlapping availability already exists for this time slot.");
            }
        }

        newAvailability.setDayOfWeek(dayOfWeek); // Save day of week
        return availabilityRepository.save(newAvailability);
    }

    @Override
    public List<Availability> getAllAvailabilities() {
        return availabilityRepository.findAll();
    }

    @Override
    public Availability getAvailabilityById(Long id) {
        return availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found"));
    }

    @Override
    public Availability updateAvailability(Long id, UpdateAvailabilityRequest request) {
        Availability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability not found"));

        availability.setStartTime(request.getStartTime().toLocalTime());
        availability.setEndTime(request.getEndTime().toLocalTime());

        return availabilityRepository.save(availability);
    }

    @Override
    public void deleteAvailability(Long id) {
        if (!availabilityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Availability not found");
        }
        availabilityRepository.deleteById(id);
    }
}
