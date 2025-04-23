package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Appointment;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;

public class AppointmentMapper {

    public static AppointmentResponse toResponse(Appointment a) {
        return new AppointmentResponse(
                a.getId(),
                a.getDateTime(),
                a.getStatus(),
                a.getPatient().getFirstName() + " " + a.getPatient().getLastName(),
                a.getDentist().getSpecialization() + " (" + a.getDentist().getEmail() + ")",
                a.getSurgery().getAddress(),
                a.getTreatmentNotes()
        );
    }
}
