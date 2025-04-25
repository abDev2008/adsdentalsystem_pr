package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.domain.Availability;
import com.abletocode.adsdentalsystem.dto.availability.CreateAvailabilityRequest;
import com.abletocode.adsdentalsystem.dto.availability.UpdateAvailabilityRequest;

import com.abletocode.adsdentalsystem.service.AvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availabilities")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<Availability> create(@Valid @RequestBody CreateAvailabilityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(availabilityService.createAvailability(request));
    }

    @GetMapping
    public ResponseEntity<List<Availability>> getAll() {
        return ResponseEntity.ok(availabilityService.getAllAvailabilities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Availability> getById(@PathVariable Long id) {
        return ResponseEntity.ok(availabilityService.getAvailabilityById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Availability> update(@PathVariable Long id, @Valid @RequestBody UpdateAvailabilityRequest request) {
        return ResponseEntity.ok(availabilityService.updateAvailability(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        availabilityService.deleteAvailability(id);
        return ResponseEntity.noContent().build();
    }
}
