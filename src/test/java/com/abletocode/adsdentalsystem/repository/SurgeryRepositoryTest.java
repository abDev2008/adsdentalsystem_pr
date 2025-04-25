package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Surgery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SurgeryRepositoryTest {

    @Autowired
    private SurgeryRepository surgeryRepository;

    @Test
    void shouldSaveAndRetrieveSurgery() {

        Surgery surgery = new Surgery();
        surgery.setName("Dental Plaza");
        surgery.setAddress("123 Main Street, NYC");
        surgery.setPhone("0700000000");


        Surgery saved = surgeryRepository.save(surgery);


        assertNotNull(saved.getId());
        assertEquals("Dental Plaza", saved.getName());
        assertEquals("123 Main Street, NYC", saved.getAddress());
    }
}
