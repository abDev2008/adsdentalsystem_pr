package com.abletocode.adsdentalsystem.mapper;

import com.abletocode.adsdentalsystem.domain.Appointment;
import com.abletocode.adsdentalsystem.domain.Bill;
import com.abletocode.adsdentalsystem.dto.bill.BillResponse;
import com.abletocode.adsdentalsystem.dto.bill.CreateBillRequest;
import org.springframework.stereotype.Component;

@Component
public class BillMapper {

    public Bill toEntity(CreateBillRequest dto, Appointment appointment) {
        Bill bill = new Bill();
        bill.setAppointment(appointment);
        bill.setAmount(dto.getAmount());
        bill.setGeneratedDate(dto.getIssuedDate()); // ✅ renamed to match entity field
        return bill;
    }

    public BillResponse toResponse(Bill entity) {
        BillResponse response = new BillResponse();
        response.setId(entity.getId());
        response.setAppointmentId(entity.getAppointment().getId());
        response.setAmount(entity.getAmount());
        response.setGeneratedDate(entity.getGeneratedDate()); // ✅ renamed
        return response;
    }
}
