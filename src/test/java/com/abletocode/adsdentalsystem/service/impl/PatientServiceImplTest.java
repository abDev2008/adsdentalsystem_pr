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
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreatePatientSuccessfully() {
        CreatePatientRequest request = new CreatePatientRequest(
                "Elena", "Smith", "elena@ads.com", "0712345678", "NYC", LocalDate.of(1993, 3, 15), "http://img.com"
        );

        Patient patient = new Patient();
        when(patientMapper.toEntity(request)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);
        when(patientMapper.toResponse(patient)).thenReturn(new PatientResponse());

        PatientResponse response = patientService.createPatient(request);
        assertNotNull(response);
        verify(patientRepository).save(patient);
    }

    @Test
    void shouldGetAllPatients() {
        List<Patient> patients = List.of(new Patient(), new Patient());
        when(patientRepository.findAll()).thenReturn(patients);
        when(patientMapper.toResponse(any())).thenReturn(new PatientResponse());

        assertEquals(2, patientService.getAllPatients().size());
    }

    @Test
    void shouldGetPatientById() {
        Patient p = new Patient(); p.setId(1L); p.setFirstName("Elena");
        when(patientRepository.findById(1L)).thenReturn(Optional.of(p));
        Patient found = patientService.getPatientById(1L);
        assertEquals(1L, found.getId());
    }

    @Test
    void shouldThrowWhenPatientNotFoundById() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> patientService.getPatientById(99L));
    }

    @Test
    void shouldDeletePatient() {
        Patient patient = new Patient(); patient.setId(1L);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        patientService.deletePatient(1L);
        verify(patientRepository).delete(patient);
    }

    @Test
    void shouldThrowWhenDeletingNonExistentPatient() {
        when(patientRepository.findById(88L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> patientService.deletePatient(88L));
    }

    @Test
    void shouldReturnAllPatients() {
        Patient p1 = new Patient(); p1.setId(1L); p1.setFirstName("Elena");
        Patient p2 = new Patient(); p2.setId(2L); p2.setFirstName("John");

        when(patientRepository.findAll()).thenReturn(List.of(p1, p2));
        when(patientMapper.toResponse(any())).thenReturn(new PatientResponse());

        List<PatientResponse> result = patientService.getAllPatients();

        assertEquals(2, result.size());
        verify(patientRepository).findAll();
    }

    @Test
    void shouldUpdatePatientSuccessfully() {
        UpdatePatientRequest request = new UpdatePatientRequest();
        request.setFirstName("Jane");
        request.setLastName("Doe");
        request.setPhone("0722222222");
        request.setAddress("New Address");
        request.setDob(LocalDate.of(1992, 6, 15));
        request.setProfilePictureUrl("http://img.com");

        Patient existing = new Patient();
        existing.setId(1L);

        when(patientRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(patientRepository.save(any())).thenReturn(existing);

        Patient result = patientService.updatePatient(1L, request);

        assertEquals("Jane", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(patientRepository).save(existing);
    }


}
