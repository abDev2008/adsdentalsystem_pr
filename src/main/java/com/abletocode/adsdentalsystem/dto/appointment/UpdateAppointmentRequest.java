package com.abletocode.adsdentalsystem.dto.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAppointmentRequest {

    @NotNull
    private Long patientId;

    @NotNull
    private Long dentistId;

    @NotNull
    private LocalDateTime appointmentDate;

    @NotBlank
    private String reason;
}
