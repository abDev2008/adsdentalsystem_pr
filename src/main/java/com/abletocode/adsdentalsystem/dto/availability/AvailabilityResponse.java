package com.abletocode.adsdentalsystem.dto.availability;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class AvailabilityResponse {

    private Long id;
    private Long dentistId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
