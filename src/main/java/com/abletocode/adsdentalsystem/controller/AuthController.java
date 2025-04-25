package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.domain.Role;
import com.abletocode.adsdentalsystem.domain.User;
import com.abletocode.adsdentalsystem.domain.enums.UserRole;
import com.abletocode.adsdentalsystem.dto.auth.JwtResponse;
import com.abletocode.adsdentalsystem.dto.auth.LoginRequest;
import com.abletocode.adsdentalsystem.dto.auth.RegisterRequest;
import com.abletocode.adsdentalsystem.repository.RoleRepository;
import com.abletocode.adsdentalsystem.repository.UserRepository;
import com.abletocode.adsdentalsystem.security.UserPrincipal;
import com.abletocode.adsdentalsystem.security.jwt.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already registered");
        }

        Role role = roleRepository.findByName(request.getRole().name())
                .orElseThrow(() -> new RuntimeException("Role not found"));




        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(role);

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }



}
