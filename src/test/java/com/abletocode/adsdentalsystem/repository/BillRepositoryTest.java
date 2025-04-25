package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.*;
import com.abletocode.adsdentalsystem.domain.enums.AppointmentStatus;
import com.abletocode.adsdentalsystem.domain.enums.BillStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BillRepositoryTest {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Test
    void shouldSaveAndRetrieveBill() {

        Patient patient = new Patient();
        patient.setFirstName("Elena");
        patient.setLastName("Smith");
        patient.setEmail("elena@ads.com");
        patient.setPhone("0711111111");
        patient.setDob(LocalDate.of(1993, 3, 15));
        patient.setAddress("New York");
        patient = patientRepository.save(patient);

        Dentist dentist = new Dentist();
        dentist.setFirstName("John");
        dentist.setLastName("Doe");
        dentist.setEmail("john@ads.com");
        dentist.setPhone("0722222222");
        dentist.setSpecialization("Surgery");
        dentist.setExperienceYears(10);
        dentist = dentistRepository.save(dentist);

        Surgery surgery = new Surgery();
        surgery.setName("City Smile");
        surgery.setAddress("Downtown");
        surgery.setPhone("0733333333");
        surgery = surgeryRepository.save(surgery);

        Appointment appointment = new Appointment();
        appointment.setDateTime(LocalDateTime.of(2025, 4, 25, 10, 0));
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setSurgery(surgery);
        appointment = appointmentRepository.save(appointment);


        Bill bill = new Bill();
        bill.setAmount(new BigDecimal("100.00"));
        bill.setStatus(BillStatus.UNPAID);
        bill.setGeneratedDate(LocalDate.now());
        bill.setAppointment(appointment);
        Bill saved = billRepository.save(bill);


        assertNotNull(saved.getId());
        assertEquals(BillStatus.UNPAID, saved.getStatus());
        assertEquals(appointment.getId(), saved.getAppointment().getId());
    }
}
