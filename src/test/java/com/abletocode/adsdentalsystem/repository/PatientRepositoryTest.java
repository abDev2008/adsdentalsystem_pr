package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void shouldSaveAndRetrievePatient() {
        Patient patient = new Patient();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setEmail("john.doe@ads.com");
        patient.setPhone("0711111111");
        patient.setAddress("New York");
        patient.setDob(LocalDate.of(1990, 5, 15));

        Patient saved = patientRepository.save(patient);

        assertNotNull(saved.getId());
        assertEquals("John", saved.getFirstName());
    }

    @Test
    void shouldFindPatientById() {
        Patient patient = new Patient();
        patient.setFirstName("Jane");
        patient.setLastName("Smith");
        patient.setEmail("jane.smith@ads.com");
        patient.setPhone("0722222222");
        patient.setAddress("Los Angeles");
        patient.setDob(LocalDate.of(1992, 7, 20));

        Patient saved = patientRepository.save(patient);

        Optional<Patient> found = patientRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("Jane", found.get().getFirstName());
    }

    @Test
    void shouldReturnAllPatients() {
        Patient p1 = new Patient();
        p1.setFirstName("Tom");
        p1.setLastName("Jones");
        p1.setEmail("tom.jones@ads.com");
        p1.setPhone("0733333333");
        p1.setAddress("Chicago");
        p1.setDob(LocalDate.of(1991, 3, 10));

        Patient p2 = new Patient();
        p2.setFirstName("Emma");
        p2.setLastName("Brown");
        p2.setEmail("emma.brown@ads.com");
        p2.setPhone("0744444444");
        p2.setAddress("Houston");
        p2.setDob(LocalDate.of(1994, 8, 5));

        patientRepository.save(p1);
        patientRepository.save(p2);

        List<Patient> patients = patientRepository.findAll();

        assertTrue(patients.size() >= 2);
    }
}
