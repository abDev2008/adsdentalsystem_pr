package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Appointment;
import com.abletocode.adsdentalsystem.domain.Dentist;
import com.abletocode.adsdentalsystem.domain.Patient;
import com.abletocode.adsdentalsystem.domain.Surgery;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;
import com.abletocode.adsdentalsystem.dto.appointment.CreateAppointmentRequest;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public Appointment toEntity(CreateAppointmentRequest dto, Dentist dentist, Patient patient, Surgery surgery) {
        Appointment appointment = new Appointment();
        appointment.setDentist(dentist);
        appointment.setPatient(patient);
        appointment.setSurgery(surgery);
        appointment.setDateTime(dto.getDateTime());
        appointment.setTreatmentNotes(dto.getTreatmentNotes());
        return appointment;
    }

    public AppointmentResponse toResponse(Appointment entity) {
        AppointmentResponse response = new AppointmentResponse();
        response.setId(entity.getId());
        response.setDateTime(entity.getDateTime());
        response.setStatus(entity.getStatus());
        response.setTreatmentNotes(entity.getTreatmentNotes());
        response.setDentistName(entity.getDentist().getFirstName() + " " + entity.getDentist().getLastName());
        response.setPatientName(entity.getPatient().getFirstName() + " " + entity.getPatient().getLastName());
        response.setSurgeryLocation(entity.getSurgery().getAddress());
        return response;
    }
}
