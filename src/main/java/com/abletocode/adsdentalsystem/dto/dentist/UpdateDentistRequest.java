package com.abletocode.adsdentalsystem.dto.dentist;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateDentistRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phone;

    private String specialization;
    private Integer experienceYears;
}
