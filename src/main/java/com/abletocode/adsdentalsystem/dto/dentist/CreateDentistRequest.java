package com.abletocode.adsdentalsystem.dto.dentist;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreateDentistRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String specialization;

    private String profilePictureUrl;
}
