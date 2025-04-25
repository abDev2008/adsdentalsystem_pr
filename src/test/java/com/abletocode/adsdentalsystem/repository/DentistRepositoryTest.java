package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Dentist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DentistRepositoryTest {

    @Autowired
    private DentistRepository dentistRepository;

    @Test
    void shouldSaveAndRetrieveDentist() {

        Dentist dentist = new Dentist();
        dentist.setFirstName("John");
        dentist.setLastName("Doe");
        dentist.setEmail("johndoe@adsdentalsystem.com");
        dentist.setPhone("0700000000");
        dentist.setSpecialization("Orthodontist");
        dentist.setExperienceYears(5);
        dentist.setProfilePictureUrl("http://example.com/profile.jpg");


        Dentist saved = dentistRepository.save(dentist);


        assertNotNull(saved.getId());
        Optional<Dentist> found = dentistRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("John", found.get().getFirstName());
        assertEquals("Doe", found.get().getLastName());
        assertEquals("Orthodontist", found.get().getSpecialization());
    }

    @Test
    void shouldDeleteDentist() {

        Dentist dentist = new Dentist();
        dentist.setFirstName("Mary");
        dentist.setLastName("Poppins");
        dentist.setEmail("mary@adsdentalsystem.com");
        dentist.setPhone("0700001111");
        dentist.setSpecialization("Prosthodontist");
        dentist.setExperienceYears(8);
        dentist.setProfilePictureUrl("http://example.com/mary.jpg");

        Dentist saved = dentistRepository.save(dentist);


        dentistRepository.delete(saved);


        Optional<Dentist> found = dentistRepository.findById(saved.getId());
        assertTrue(found.isEmpty());
    }
}
