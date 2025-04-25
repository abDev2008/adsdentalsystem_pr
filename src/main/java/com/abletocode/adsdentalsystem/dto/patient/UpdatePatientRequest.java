package com.abletocode.adsdentalsystem.dto.patient;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UpdatePatientRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;

    @Past
    private LocalDate dob;

    private String profilePictureUrl;
}
