package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.dto.appointment.CreateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.UpdateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    AppointmentResponse createAppointment(CreateAppointmentRequest request);
    List<AppointmentResponse> getAllAppointments();
    List<AppointmentResponse> getAppointmentsByPatientId(Long patientId);
    AppointmentResponse getAppointmentById(Long id);
    AppointmentResponse updateAppointment(Long id, UpdateAppointmentRequest request);
    void deleteAppointment(Long id);
}
