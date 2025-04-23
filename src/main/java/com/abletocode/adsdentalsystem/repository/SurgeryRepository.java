package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurgeryRepository extends JpaRepository<Surgery, Long> {
}
