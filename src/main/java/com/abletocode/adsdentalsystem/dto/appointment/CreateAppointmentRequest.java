package com.abletocode.adsdentalsystem.dto.appointment;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreateAppointmentRequest {

    @NotNull
    private Long patientId;

    @NotNull
    private Long dentistId;

    @NotNull
    private Long surgeryId;

    @Future
    private LocalDateTime appointmentTime;

    @Size(max = 1000)
    private String treatmentNotes;
}
