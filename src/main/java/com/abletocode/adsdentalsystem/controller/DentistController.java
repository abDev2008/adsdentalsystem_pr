package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.service.DentistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dentists")
@RequiredArgsConstructor
public class DentistController {

    private final DentistService dentistService;

    @PostMapping
    public ResponseEntity<Dentist> create(@RequestBody @Valid CreateDentistRequest request) {
        Dentist saved = dentistService.createDentist(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
