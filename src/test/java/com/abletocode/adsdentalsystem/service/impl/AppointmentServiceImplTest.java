package com.abletocode.adsdentalsystem.service.impl;


import com.abletocode.adsdentalsystem.domain.*;
import com.abletocode.adsdentalsystem.domain.enums.AppointmentStatus;
import com.abletocode.adsdentalsystem.domain.enums.BillStatus;
import com.abletocode.adsdentalsystem.dto.appointment.CreateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;
import com.abletocode.adsdentalsystem.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

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

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateAppointmentAndGenerateBill() {
        // Arrange
        Long patientId = 1L;
        Long dentistId = 2L;
        Long surgeryId = 3L;
        LocalDateTime appointmentTime = LocalDateTime.of(2025, 4, 30, 10, 0);

        CreateAppointmentRequest request = new CreateAppointmentRequest(
                patientId, dentistId, surgeryId, appointmentTime, "Routine checkup"
        );

        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setFirstName("Elena");
        patient.setLastName("Smith");
        patient.setAppointments(List.of());

        Dentist dentist = new Dentist();
        dentist.setId(dentistId);
        dentist.setEmail("dr.adams@adsdental.com");
        dentist.setSpecialization("Orthodontics");
        dentist.setAppointments(List.of());

        Surgery surgery = new Surgery();
        surgery.setId(surgeryId);

        Appointment appointment = new Appointment();
        appointment.setId(10L);
        appointment.setDateTime(request.getDateTime());
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointment.setTreatmentNotes(request.getTreatmentNotes());
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setSurgery(surgery);

        when(patientRepo.findById(patientId)).thenReturn(Optional.of(patient));
        when(dentistRepo.findById(dentistId)).thenReturn(Optional.of(dentist));
        when(surgeryRepo.findById(surgeryId)).thenReturn(Optional.of(surgery));
        when(appointmentRepo.findAll()).thenReturn(Collections.emptyList());
        when(appointmentRepo.save(any(Appointment.class))).thenReturn(appointment);
        when(billRepo.save(any(Bill.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        AppointmentResponse response = appointmentService.createAppointment(request);

        // Assert
        assertNotNull(response);
        assertEquals(AppointmentStatus.CONFIRMED, response.getStatus());
        assertEquals("Routine checkup", response.getTreatmentNotes());
        assertTrue(response.getPatientName().contains("Elena"));
        assertTrue(response.getDentistName().contains("Orthodontics"));
        verify(appointmentRepo).save(any(Appointment.class));
        verify(billRepo).save(any(Bill.class));
    }

    @Test
    void shouldThrowExceptionWhenPatientHasUnpaidBill() {
        // Arrange
        Long patientId = 1L;
        Long dentistId = 2L;
        Long surgeryId = 3L;
        LocalDateTime appointmentTime = LocalDateTime.of(2025, 4, 30, 11, 0);

        CreateAppointmentRequest request = new CreateAppointmentRequest(
                patientId, dentistId, surgeryId, appointmentTime, "Follow-up"
        );

        Bill unpaidBill = new Bill();
        unpaidBill.setStatus(BillStatus.UNPAID);

        Appointment pastAppointment = new Appointment();
        pastAppointment.setBill(unpaidBill);

        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setAppointments(List.of(pastAppointment));

        Dentist dentist = new Dentist();
        dentist.setId(dentistId);
        dentist.setAppointments(List.of());

        Surgery surgery = new Surgery();
        surgery.setId(surgeryId);

        when(patientRepo.findById(patientId)).thenReturn(Optional.of(patient));
        when(dentistRepo.findById(dentistId)).thenReturn(Optional.of(dentist));
        when(surgeryRepo.findById(surgeryId)).thenReturn(Optional.of(surgery));
        when(appointmentRepo.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> appointmentService.createAppointment(request)
        );

        assertEquals("Patient has unpaid bills and cannot book a new appointment.", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDentistHas5AppointmentsThisWeek() {
        // Arrange
        Long patientId = 1L;
        Long dentistId = 2L;
        Long surgeryId = 3L;
        LocalDateTime appointmentTime = LocalDateTime.of(2025, 5, 2, 9, 0); // Any date in same week

        CreateAppointmentRequest request = new CreateAppointmentRequest(
                patientId, dentistId, surgeryId, appointmentTime, "Teeth whitening"
        );

        // 5 dummy appointments for the same week
        List<Appointment> existingAppointments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Appointment appt = new Appointment();
            appt.setDateTime(LocalDateTime.of(2025, 5, 1, 10 + i, 0)); // same DAY, different TIME
            existingAppointments.add(appt);
        }


        Dentist dentist = new Dentist();
        dentist.setId(dentistId);
        dentist.setAppointments(existingAppointments);

        Patient patient = new Patient();
        patient.setId(patientId);
        patient.setAppointments(List.of());

        Surgery surgery = new Surgery();
        surgery.setId(surgeryId);

        when(patientRepo.findById(patientId)).thenReturn(Optional.of(patient));
        when(dentistRepo.findById(dentistId)).thenReturn(Optional.of(dentist));
        when(surgeryRepo.findById(surgeryId)).thenReturn(Optional.of(surgery));
        when(appointmentRepo.findAll()).thenReturn(Collections.emptyList());

        // Act & Assert
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> appointmentService.createAppointment(request)
        );

        assertEquals("Dentist already has 5 appointments this week.", ex.getMessage());

    }

    @Test
    void shouldThrowExceptionWhenAppointmentConflicts() {
        // Arrange
        Long patientId = 1L;
        Long dentistId = 2L;
        Long surgeryId = 3L;
        LocalDateTime conflictingTime = LocalDateTime.of(2025, 5, 3, 15, 0);

        CreateAppointmentRequest request = new CreateAppointmentRequest(
                patientId, dentistId, surgeryId, conflictingTime, "Conflict test"
        );

        // Existing appointment with same time and same dentist (conflict)
        Patient otherPatient = new Patient();
        otherPatient.setId(99L); // different patient

        Dentist sameDentist = new Dentist();
        sameDentist.setId(dentistId);

        Appointment existing = new Appointment();
        existing.setDateTime(conflictingTime);
        existing.setDentist(sameDentist);
        existing.setPatient(otherPatient); // same time + same dentist

        // Setup test patient and dentist
        Patient requestPatient = new Patient();
        requestPatient.setId(patientId);
        requestPatient.setAppointments(List.of());

        Dentist requestDentist = new Dentist();
        requestDentist.setId(dentistId);
        requestDentist.setAppointments(List.of());

        Surgery surgery = new Surgery();
        surgery.setId(surgeryId);

        when(patientRepo.findById(patientId)).thenReturn(Optional.of(requestPatient));
        when(dentistRepo.findById(dentistId)).thenReturn(Optional.of(requestDentist));
        when(surgeryRepo.findById(surgeryId)).thenReturn(Optional.of(surgery));
        when(appointmentRepo.findAll()).thenReturn(List.of(existing));

        // Act & Assert
        IllegalStateException ex = assertThrows(
                IllegalStateException.class,
                () -> appointmentService.createAppointment(request)
        );

        assertEquals("Conflicting appointment found for dentist or patient.", ex.getMessage());

    }


}
