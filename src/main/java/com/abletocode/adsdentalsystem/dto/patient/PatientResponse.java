package com.abletocode.adsdentalsystem.dto.patient;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PatientResponse {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dob;
}
