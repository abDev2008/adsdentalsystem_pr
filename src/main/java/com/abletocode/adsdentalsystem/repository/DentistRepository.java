package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepository extends JpaRepository<Dentist, Long> {
}
