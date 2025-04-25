package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.dto.patient.CreatePatientRequest;
import com.abletocode.adsdentalsystem.dto.patient.PatientResponse;
import com.abletocode.adsdentalsystem.dto.patient.UpdatePatientRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.PatientMapper;
import com.abletocode.adsdentalsystem.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private PatientMapper patientMapper;

    @InjectMocks
    private PatientServiceImpl patientService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatePatient() {
        CreatePatientRequest request = new CreatePatientRequest(
                "Elena",
                "Smith",
                "elena@ads.com",
                "0712345678",
                "NYC",
                LocalDate.of(1993, 3, 15),
                "http://img.com"
        );

        Patient patient = new Patient(); patient.setId(1L);
        PatientResponse response = new PatientResponse(); response.setId(1L);

        when(patientMapper.toEntity(request)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toResponse(patient)).thenReturn(response);

        PatientResponse result = patientService.createPatient(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldReturnAllPatients() {
        List<Patient> patients = List.of(new Patient(), new Patient());
        List<PatientResponse> responses = List.of(new PatientResponse(), new PatientResponse());

        when(patientRepository.findAll()).thenReturn(patients);
        when(patientMapper.toResponse(any())).thenReturn(new PatientResponse());

        List<PatientResponse> result = patientService.getAllPatients();

        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnPatientById() {
        Patient patient = new Patient(); patient.setId(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        Patient result = patientService.getPatientById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void shouldUpdatePatient() {
        UpdatePatientRequest request = UpdatePatientRequest.builder()
                .firstName("Jane")
                .lastName("Doe")
                .phone("0722222222")
                .address("Surgery")
                .dob(LocalDate.of(1992, 6, 15))
                .profilePictureUrl("http://img.com")
                .build();


        Patient existing = new Patient(); existing.setId(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(patientRepository.save(any())).thenReturn(existing);

        Patient result = patientService.updatePatient(1L, request);

        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
    }

    @Test
    void shouldDeletePatient() {
        Patient patient = new Patient(); patient.setId(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        patientService.deletePatient(1L);
        verify(patientRepository).delete(patient);
    }

    @Test
    void shouldThrowIfPatientNotFound() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById(99L));
    }
}
