package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
