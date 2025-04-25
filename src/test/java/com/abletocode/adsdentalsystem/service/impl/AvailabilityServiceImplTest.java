package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Availability;
import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.availability.CreateAvailabilityRequest;
import com.abletocode.adsdentalsystem.dto.availability.UpdateAvailabilityRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.AvailabilityMapper;
import com.abletocode.adsdentalsystem.repository.AvailabilityRepository;
import com.abletocode.adsdentalsystem.repository.DentistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvailabilityServiceImplTest {

    @Mock private AvailabilityRepository availabilityRepo;
    @Mock private DentistRepository dentistRepo;
    @Mock private AvailabilityMapper availabilityMapper;

    @InjectMocks
    private AvailabilityServiceImpl availabilityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAvailability() {
        CreateAvailabilityRequest req = new CreateAvailabilityRequest();
        req.setDentistId(1L);
        req.setStartTime(LocalDateTime.now().plusDays(1));
        req.setEndTime(LocalDateTime.now().plusDays(1).plusHours(2));

        Dentist dentist = new Dentist();
        dentist.setId(1L);
        Availability availability = new Availability();

        when(dentistRepo.findById(1L)).thenReturn(Optional.of(dentist));
        when(availabilityMapper.toEntity(req, dentist)).thenReturn(availability);
        when(availabilityRepo.save(availability)).thenReturn(availability);

        Availability saved = availabilityService.createAvailability(req);

        assertNotNull(saved);
        verify(availabilityRepo).save(availability);
    }

    @Test
    void shouldGetAllAvailabilities() {
        when(availabilityRepo.findAll()).thenReturn(List.of(new Availability(), new Availability()));
        List<Availability> result = availabilityService.getAllAvailabilities();
        assertEquals(2, result.size());
    }

    @Test
    void shouldGetAvailabilityById() {
        Availability availability = new Availability(); availability.setId(1L);
        when(availabilityRepo.findById(1L)).thenReturn(Optional.of(availability));

        Availability found = availabilityService.getAvailabilityById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void shouldThrowWhenAvailabilityNotFound() {
        when(availabilityRepo.findById(88L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> availabilityService.getAvailabilityById(88L));
    }

    @Test
    void shouldUpdateAvailability() {
        UpdateAvailabilityRequest req = new UpdateAvailabilityRequest();
        req.setStartTime(LocalDateTime.now().plusDays(1));
        req.setEndTime(LocalDateTime.now().plusDays(1).plusHours(1));

        Availability existing = new Availability(); existing.setId(1L);

        when(availabilityRepo.findById(1L)).thenReturn(Optional.of(existing));
        when(availabilityRepo.save(existing)).thenReturn(existing);

        Availability updated = availabilityService.updateAvailability(1L, req);

        assertNotNull(updated);
        assertEquals(existing.getId(), updated.getId());
    }

    @Test
    void shouldDeleteAvailability() {
        when(availabilityRepo.existsById(1L)).thenReturn(true);
        doNothing().when(availabilityRepo).deleteById(1L);
        availabilityService.deleteAvailability(1L);
        verify(availabilityRepo).deleteById(1L);
    }

    @Test
    void shouldThrowWhenDeletingNonExistentAvailability() {
        when(availabilityRepo.existsById(99L)).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> availabilityService.deleteAvailability(99L));
    }
}
