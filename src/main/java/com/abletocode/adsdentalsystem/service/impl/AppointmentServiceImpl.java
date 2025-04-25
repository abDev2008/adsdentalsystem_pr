package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.*;
import com.abletocode.adsdentalsystem.domain.enums.AppointmentStatus;
import com.abletocode.adsdentalsystem.domain.enums.BillStatus;
import com.abletocode.adsdentalsystem.dto.appointment.CreateAppointmentRequest;
import com.abletocode.adsdentalsystem.dto.appointment.AppointmentResponse;
import com.abletocode.adsdentalsystem.dto.appointment.UpdateAppointmentRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.mapper.AppointmentMapper;
import com.abletocode.adsdentalsystem.repository.*;
import com.abletocode.adsdentalsystem.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepo;
    private final PatientRepository patientRepo;
    private final DentistRepository dentistRepo;
    private final SurgeryRepository surgeryRepo;
    private final BillRepository billRepo;
    private final AppointmentMapper appointmentMapper;

    @Override
    @Transactional
    public AppointmentResponse createAppointment(CreateAppointmentRequest req) {
        Patient patient = patientRepo.findById(req.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Dentist dentist = dentistRepo.findById(req.getDentistId())
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found"));

        Surgery surgery = surgeryRepo.findById(req.getSurgeryId())
                .orElseThrow(() -> new ResourceNotFoundException("Surgery not found"));

        boolean hasUnpaid = patient.getAppointments().stream()
                .map(Appointment::getBill)
                .anyMatch(bill -> bill.getStatus() == BillStatus.UNPAID);

        if (hasUnpaid)
            throw new IllegalStateException("Patient has unpaid bills and cannot book a new appointment.");

        int currentWeek = req.getDateTime().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

        long weeklyAppointments = dentist.getAppointments().stream()
                .filter(a -> a.getDateTime().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()) == currentWeek)
                .count();

        if (weeklyAppointments >= 5)
            throw new IllegalStateException("Dentist already has 5 appointments this week.");

        boolean hasConflict = appointmentRepo.findAll().stream()
                .anyMatch(a -> a.getDateTime().equals(req.getDateTime()) &&
                        (a.getDentist().getId().equals(dentist.getId()) ||
                                a.getPatient().getId().equals(patient.getId())));

        if (hasConflict)
            throw new IllegalStateException("Conflicting appointment found for dentist or patient.");

        Appointment appointment = new Appointment();
        appointment.setDateTime(req.getDateTime());
        appointment.setStatus(AppointmentStatus.CONFIRMED);
        appointment.setTreatmentNotes(req.getTreatmentNotes());
        appointment.setPatient(patient);
        appointment.setDentist(dentist);
        appointment.setSurgery(surgery);

        appointment = appointmentRepo.save(appointment);

        Bill bill = new Bill();
        bill.setAmount(new BigDecimal("100.00"));
        bill.setStatus(BillStatus.UNPAID);
        bill.setGeneratedDate(LocalDate.now());
        bill.setAppointment(appointment);

        billRepo.save(bill);
        appointment.setBill(bill);

        return appointmentMapper.toResponse(appointment);
    }

    @Override
    public List<AppointmentResponse> getAllAppointments() {
        return appointmentRepo.findAll()
                .stream()
                .map(appointmentMapper::toResponse)
                .toList();
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByPatientId(Long patientId) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        return patient.getAppointments()
                .stream()
                .map(appointmentMapper::toResponse)
                .toList();
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        appointmentRepo.delete(appointment);
    }

    @Override
    public AppointmentResponse updateAppointment(Long id, UpdateAppointmentRequest request) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));

        Dentist dentist = dentistRepo.findById(request.getDentistId())
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found"));

        Patient patient = patientRepo.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        appointment.setDateTime(request.getAppointmentDate());
        appointment.setDentist(dentist);
        appointment.setPatient(patient);
        appointment.setTreatmentNotes(request.getReason());

        Appointment updated = appointmentRepo.save(appointment);
        return appointmentMapper.toResponse(updated);
    }

    @Override
    public AppointmentResponse getAppointmentById(Long id) {
        Appointment appointment = appointmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
        return appointmentMapper.toResponse(appointment);
    }
}
