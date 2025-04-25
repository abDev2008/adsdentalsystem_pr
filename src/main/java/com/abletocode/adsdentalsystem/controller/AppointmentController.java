package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.domain.Appointment;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;
import com.abletocode.adsdentalsystem.dto.appointment.CreateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.UpdateAppointmentRequest;
import com.abletocode.adsdentalsystem.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@Valid @RequestBody CreateAppointmentRequest request) {
        AppointmentResponse created = appointmentService.createAppointment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }


    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAll() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getById(@PathVariable Long id) {
        AppointmentResponse response = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateAppointmentRequest request
    ) {
        AppointmentResponse response = appointmentService.updateAppointment(id, request);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
