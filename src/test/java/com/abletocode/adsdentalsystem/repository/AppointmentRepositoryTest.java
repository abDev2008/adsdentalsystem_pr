package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.*;
import com.abletocode.adsdentalsystem.domain.enums.AppointmentStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Test
    void shouldSaveAndRetrieveAppointment() {
        Patient patient = new Patient();
        patient.setFirstName("Elena");
        patient.setLastName("Smith");
        patient.setEmail("elena@ads.com");
        patient.setPhone("0700000000");
        patient.setAddress("New York");
        patient.setDob(LocalDate.of(1993, 3, 15));
        patient = patientRepository.save(patient);

        Dentist dentist = new Dentist();
        dentist.setFirstName("John");
        dentist.setLastName("Doe");
        dentist.setEmail("john.doe@ads.com");
        dentist.setPhone("0711111111");
        dentist.setSpecialization("Orthodontist");
        dentist = dentistRepository.save(dentist);

        Surgery surgery = new Surgery();
        surgery.setName("Smile Clinic");
        surgery.setAddress("123 Dental St.");
        surgery.setPhone("0700000000");
        surgery = surgeryRepository.save(surgery);

        Appointment appointment = new Appointment();
        appointment.setDateTime(LocalDateTime.now().plusDays(2));
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setSurgery(surgery);
        appointment.setTreatmentNotes("Routine cleaning");
        appointment = appointmentRepository.save(appointment);

        assertNotNull(appointment.getId());
        assertEquals(AppointmentStatus.CONFIRMED, appointment.getStatus());
        assertEquals("Elena", appointment.getPatient().getFirstName());
        assertEquals("John", appointment.getDentist().getFirstName());
    }
}
