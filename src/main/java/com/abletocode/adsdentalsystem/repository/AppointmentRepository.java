package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
