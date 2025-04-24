package com.abletocode.adsdentalsystem.dto.auth;

import com.abletocode.adsdentalsystem.domain.enums.UserRole;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserRole role;
}
