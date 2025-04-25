package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Availability;
import com.abletocode.adsdentalsystem.domain.Dentist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AvailabilityRepositoryTest {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Test
    void shouldSaveAndRetrieveAvailability() {

        Dentist dentist = new Dentist();
        dentist.setFirstName("John");
        dentist.setLastName("Smith");
        dentist.setEmail("johnsmith@ads.com");
        dentist.setPhone("0711111111");
        dentist.setSpecialization("Orthodontist");
        dentist.setExperienceYears(8);
        dentist = dentistRepository.save(dentist);


        Availability availability = new Availability();
        availability.setDayOfWeek(DayOfWeek.MONDAY);
        availability.setStartTime(LocalTime.of(9, 0));
        availability.setEndTime(LocalTime.of(17, 0));
        availability.setDentist(dentist);

        Availability saved = availabilityRepository.save(availability);

        assertNotNull(saved.getId());
        assertEquals(DayOfWeek.MONDAY, saved.getDayOfWeek());
        assertEquals(LocalTime.of(9, 0), saved.getStartTime());
        assertEquals("John", saved.getDentist().getFirstName());
    }
}
