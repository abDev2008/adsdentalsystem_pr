package com.abletocode.adsdentalsystem.dto.appointment;

import com.abletocode.adsdentalsystem.domain.enums.AppointmentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime dateTime;
    private AppointmentStatus status;
    private String patientName;
    private String dentistName;
    private String surgeryLocation;
    private String treatmentNotes;
}
