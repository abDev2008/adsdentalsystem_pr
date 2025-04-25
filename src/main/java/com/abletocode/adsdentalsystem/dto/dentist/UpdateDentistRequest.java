package com.abletocode.adsdentalsystem.dto.dentist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class UpdateDentistRequest {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phone;

    @NotBlank
    private String specialization;

    @NotNull
    private Integer experienceYears;

    private String profilePictureUrl;
}
