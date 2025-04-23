package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.dto.appointment.CreateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;

public interface AppointmentService {
    AppointmentResponse createAppointment(CreateAppointmentRequest request);
}
