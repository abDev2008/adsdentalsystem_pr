package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.dto.dentist.DentistResponse;
import com.abletocode.adsdentalsystem.service.DentistService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dentists")
@RequiredArgsConstructor
public class DentistController {

    private final DentistService dentistService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody com.abletocode.adsdentalsystem.dto.dentist.CreateDentistRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistService.createDentist(request));
    }

    @GetMapping
    public ResponseEntity<Page<DentistResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(dentistService.getAllDentists(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(dentistService.getDentistById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistResponse> update(@PathVariable Long id, @Valid @RequestBody com.abletocode.adsdentalsystem.dto.dentist.UpdateDentistRequest request) {
        return ResponseEntity.ok(dentistService.updateDentist(id, request));
    }
}
