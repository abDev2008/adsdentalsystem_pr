package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;
import com.abletocode.adsdentalsystem.mapper.PatientMapper;
import com.abletocode.adsdentalsystem.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatePatientSuccessfully() {
        // Arrange
        CreatePatientRequest request = new CreatePatientRequest(
                "Elena", "Smith", "elena@ads.com", "0712345678", "NYC", LocalDate.of(1993, 3, 15)
        );

        Patient mockPatient = PatientMapper.toEntity(request);
        mockPatient.setId(1L);

        when(patientRepository.save(any(Patient.class))).thenReturn(mockPatient);

        // Act
        PatientResponse response = patientService.createPatient(request);

        // Assert
        assertEquals("Elena Smith", response.getFullName());
        assertEquals("elena@ads.com", response.getEmail());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }
}
