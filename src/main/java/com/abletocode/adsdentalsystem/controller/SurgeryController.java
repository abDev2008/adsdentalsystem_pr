package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.domain.Surgery;
import com.abletocode.adsdentalsystem.dto.surgery.CreateSurgeryRequest;
import com.abletocode.adsdentalsystem.service.SurgeryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/surgeries")
@RequiredArgsConstructor
public class SurgeryController {

    private final SurgeryService surgeryService;

    @PostMapping
    public ResponseEntity<Surgery> create(@RequestBody @Valid CreateSurgeryRequest request) {
        Surgery saved = surgeryService.createSurgery(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
}
