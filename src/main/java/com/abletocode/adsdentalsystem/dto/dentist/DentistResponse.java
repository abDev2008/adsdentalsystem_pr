package com.abletocode.adsdentalsystem.dto.dentist;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class DentistResponse {
    private Long id;
    private String email;
    private String phone;
    private String specialization;
    private String profilePictureUrl;
    private String firstName;
    private String lastName;
    private int experienceYears;
}
