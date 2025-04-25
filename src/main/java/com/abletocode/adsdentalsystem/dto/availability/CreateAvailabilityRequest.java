package com.abletocode.adsdentalsystem.dto.availability;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class CreateAvailabilityRequest {

    @NotNull
    private Long dentistId;

    @NotNull @Future
    private LocalDateTime startTime;

    @NotNull @Future
    private LocalDateTime endTime;
}
