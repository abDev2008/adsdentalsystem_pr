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

import java.util.*;

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
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateDentist() {
        CreateDentistRequest request = new CreateDentistRequest(
                "dr@ads.com", "0711223344", "Ortho", "http://img.com",
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
    void shouldGetAllDentists() {
        when(dentistRepository.findAll()).thenReturn(List.of(new Dentist(), new Dentist()));
        List<Dentist> all = dentistService.getAllDentists();
        assertEquals(2, all.size());
    }

    @Test
    void shouldGetDentistById() {
        Dentist dentist = new Dentist(); dentist.setId(1L);
        when(dentistRepository.findById(1L)).thenReturn(Optional.of(dentist));
        Dentist found = dentistService.getDentistById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void shouldThrowIfDentistNotFoundById() {
        when(dentistRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dentistService.getDentistById(99L));
    }

    @Test
    void shouldUpdateDentist() {
        Dentist existing = new Dentist(); existing.setId(1L);
        UpdateDentistRequest request = new UpdateDentistRequest(
                "Jane", "Doe", "0700000000", "Surgery", 8, "http://img.com"
        );

        when(dentistRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(dentistRepository.save(existing)).thenReturn(existing);
        when(dentistMapper.toResponse(existing)).thenReturn(new DentistResponse());

        DentistResponse response = dentistService.updateDentist(1L, request);
        assertNotNull(response);
        verify(dentistRepository).save(existing);
    }

    @Test
    void shouldDeleteDentist() {
        Dentist dentist = new Dentist(); dentist.setId(1L);
        when(dentistRepository.findById(1L)).thenReturn(Optional.of(dentist));
        dentistService.deleteDentist(1L);
        verify(dentistRepository).delete(dentist);
    }

    @Test
    void shouldThrowIfDeletingNonExistentDentist() {
        when(dentistRepository.findById(88L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> dentistService.deleteDentist(88L));
    }
}
