package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.dto.appointment.CreateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;
import com.abletocode.adsdentalsystem.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@RequestBody @Valid CreateAppointmentRequest request) {
        AppointmentResponse response = appointmentService.createAppointment(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
