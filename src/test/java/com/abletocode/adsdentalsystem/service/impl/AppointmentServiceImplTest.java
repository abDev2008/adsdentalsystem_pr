package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.*;
import com.abletocode.adsdentalsystem.domain.enums.AppointmentStatus;
import com.abletocode.adsdentalsystem.domain.enums.BillStatus;
import com.abletocode.adsdentalsystem.dto.appointment.CreateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.UpdateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.AppointmentMapper;
import com.abletocode.adsdentalsystem.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceImplTest {

    @Mock
    private AppointmentRepository appointmentRepo;
    @Mock
    private PatientRepository patientRepo;
    @Mock
    private DentistRepository dentistRepo;
    @Mock
    private SurgeryRepository surgeryRepo;
    @Mock
    private BillRepository billRepo;
    @Mock
    private AppointmentMapper appointmentMapper;

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAppointmentAndGenerateBill() {
        Long patientId = 1L, dentistId = 2L, surgeryId = 3L;
        LocalDateTime time = LocalDateTime.of(2025, 4, 30, 10, 0);
        CreateAppointmentRequest request = new CreateAppointmentRequest(patientId, dentistId, surgeryId, time, "Checkup");

        Patient patient = new Patient(); patient.setId(patientId); patient.setAppointments(List.of());
        Dentist dentist = new Dentist(); dentist.setId(dentistId); dentist.setAppointments(List.of());
        Surgery surgery = new Surgery(); surgery.setId(surgeryId);
        Appointment appointment = new Appointment(); appointment.setId(1L); appointment.setDateTime(time); appointment.setPatient(patient); appointment.setDentist(dentist); appointment.setSurgery(surgery);

        when(patientRepo.findById(patientId)).thenReturn(Optional.of(patient));
        when(dentistRepo.findById(dentistId)).thenReturn(Optional.of(dentist));
        when(surgeryRepo.findById(surgeryId)).thenReturn(Optional.of(surgery));
        when(appointmentRepo.findAll()).thenReturn(Collections.emptyList());
        when(appointmentRepo.save(any())).thenReturn(appointment);
        when(billRepo.save(any())).thenReturn(new Bill());
        when(appointmentMapper.toResponse(any())).thenReturn(new AppointmentResponse());

        AppointmentResponse response = appointmentService.createAppointment(request);
        assertNotNull(response);
    }

    @Test
    void shouldUpdateAppointment() {
        Long id = 1L;
        LocalDateTime newDate = LocalDateTime.of(2025, 5, 1, 14, 0);

        UpdateAppointmentRequest req = new UpdateAppointmentRequest(1L, 2L, newDate, "Updated");



        Patient patient = new Patient(); patient.setId(1L);
        Dentist dentist = new Dentist(); dentist.setId(2L);
        Appointment existing = new Appointment(); existing.setId(id); existing.setPatient(patient); existing.setDentist(dentist);

        when(appointmentRepo.findById(id)).thenReturn(Optional.of(existing));
        when(dentistRepo.findById(2L)).thenReturn(Optional.of(dentist));
        when(patientRepo.findById(1L)).thenReturn(Optional.of(patient));
        when(appointmentRepo.save(any())).thenReturn(existing);
        when(appointmentMapper.toResponse(any())).thenReturn(new AppointmentResponse());

        AppointmentResponse response = appointmentService.updateAppointment(id, req);
        assertNotNull(response);
    }

    @Test
    void shouldDeleteAppointment() {
        Long id = 1L;
        Appointment appt = new Appointment(); appt.setId(id);

        when(appointmentRepo.findById(id)).thenReturn(Optional.of(appt));
        doNothing().when(appointmentRepo).delete(appt);

        appointmentService.deleteAppointment(id);
        verify(appointmentRepo).delete(appt);
    }

    @Test
    void shouldThrowIfAppointmentNotFoundOnDelete() {
        when(appointmentRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> appointmentService.deleteAppointment(99L));
    }
}
