package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Bill;
import com.abletocode.adsdentalsystem.domain.enums.BillStatus;
import com.abletocode.adsdentalsystem.dto.bill.BillResponse;
import com.abletocode.adsdentalsystem.dto.bill.PayBillRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.repository.BillRepository;
import com.abletocode.adsdentalsystem.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    private final BillRepository billRepo;

    @Override
    public BillResponse payBill(PayBillRequest request) {
        Bill bill = billRepo.findById(request.getBillId())
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));

        if (bill.getStatus() == BillStatus.PAID)
            throw new IllegalStateException("Bill is already paid.");

        bill.setStatus(BillStatus.PAID);
        billRepo.save(bill);

        return new BillResponse(
                bill.getId(),
                bill.getAppointment().getId(),
                bill.getAmount(),
                bill.getStatus(),
                bill.getGeneratedDate()
        );
    }

    @Override
    public List<BillResponse> getAllBills() {
        return billRepo.findAll()
                .stream()
                .map(bill -> new BillResponse(
                        bill.getId(),
                        bill.getAppointment().getId(),
                        bill.getAmount(),
                        bill.getStatus(),
                        bill.getGeneratedDate()
                ))
                .toList();
    }

    @Override
    public BillResponse getBillById(Long id) {
        Bill bill = billRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bill not found"));
        return new BillResponse(
                bill.getId(),
                bill.getAppointment().getId(),
                bill.getAmount(),
                bill.getStatus(),
                bill.getGeneratedDate()
        );
    }
}
