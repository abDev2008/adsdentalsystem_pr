package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.dto.dentist.DentistResponse;
import com.abletocode.adsdentalsystem.dto.dentist.UpdateDentistRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.DentistMapper;
import com.abletocode.adsdentalsystem.repository.DentistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DentistServiceImplTest {

    @Mock
    private DentistRepository dentistRepository;

    @Mock
    private DentistMapper dentistMapper;

    @InjectMocks
    private DentistServiceImpl dentistService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateDentistSuccessfully() {
        CreateDentistRequest request = new CreateDentistRequest(
                "elena@ads.com", "0712345678", "Orthodontist", "http://img.com",
                "Elena", "Smith", 5
        );


        Dentist dentist = new Dentist();
        when(dentistMapper.toEntity(request)).thenReturn(dentist);
        when(dentistRepository.save(dentist)).thenReturn(dentist);

        Dentist result = dentistService.createDentist(request);

        assertNotNull(result);
        verify(dentistRepository).save(dentist);
    }

    @Test
    void shouldUpdateDentistSuccessfully() {
        Long id = 1L;
        UpdateDentistRequest request = new UpdateDentistRequest(
                "Jane", "Doe", "0799999999", "Surgery", 10, "http://img.com"
        );


        Dentist existing = new Dentist();
        existing.setId(id);

        when(dentistRepository.findById(id)).thenReturn(Optional.of(existing));
        when(dentistRepository.save(existing)).thenReturn(existing);
        when(dentistMapper.toResponse(existing)).thenReturn(new DentistResponse());

        DentistResponse response = dentistService.updateDentist(id, request);

        assertNotNull(response);
        verify(dentistRepository).save(existing);
    }

    @Test
    void shouldThrowWhenDentistNotFoundOnUpdate() {
        Long id = 10L;
        UpdateDentistRequest request = new UpdateDentistRequest();

        when(dentistRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> dentistService.updateDentist(id, request));
    }

    @Test
    void shouldDeleteDentistSuccessfully() {
        Long id = 5L;
        Dentist dentist = new Dentist();
        dentist.setId(id);

        when(dentistRepository.findById(id)).thenReturn(Optional.of(dentist));

        assertDoesNotThrow(() -> dentistService.deleteDentist(id));
        verify(dentistRepository).delete(dentist);
    }
}
