package com.abletocode.adsdentalsystem.dto.surgery;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CreateSurgeryRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;
}
