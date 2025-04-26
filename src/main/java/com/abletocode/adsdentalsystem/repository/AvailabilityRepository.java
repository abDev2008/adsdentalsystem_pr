package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    List<Availability> findByDentistIdAndDayOfWeek(Long dentistId, java.time.DayOfWeek dayOfWeek);

}
