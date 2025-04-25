package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest;
import com.abletocode.adsdentalsystem.dto.dentist.DentistResponse;
import com.abletocode.adsdentalsystem.dto.dentist.UpdateDentistRequest;
import com.abletocode.adsdentalsystem.service.DentistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentists")
@RequiredArgsConstructor
public class DentistController {

    private final DentistService dentistService;

    @PostMapping
    public ResponseEntity<Dentist> create(@Valid @RequestBody CreateDentistRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistService.createDentist(request));
    }

    @GetMapping
    public ResponseEntity<List<Dentist>> getAll() {
        return ResponseEntity.ok(dentistService.getAllDentists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dentist> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dentistService.getDentistById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateDentistRequest request) {
        return ResponseEntity.ok(dentistService.updateDentist(id, request));
    }
}
