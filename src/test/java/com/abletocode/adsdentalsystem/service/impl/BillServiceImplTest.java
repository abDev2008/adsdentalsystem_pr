package com.abletocode.adsdentalsystem.service.impl;

import com.abletocode.adsdentalsystem.domain.Appointment;
import com.abletocode.adsdentalsystem.domain.Bill;
import com.abletocode.adsdentalsystem.domain.enums.BillStatus;
import com.abletocode.adsdentalsystem.dto.bill.BillResponse;
import com.abletocode.adsdentalsystem.dto.bill.PayBillRequest;
import com.abletocode.adsdentalsystem.exception.ResourceNotFoundException;
import com.abletocode.adsdentalsystem.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BillServiceImplTest {

    @Mock
    private BillRepository billRepo;

    @InjectMocks
    private BillServiceImpl billService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllBills() {
        Bill bill = new Bill();
        bill.setId(1L);
        bill.setAppointment(new Appointment());
        bill.setAmount(BigDecimal.valueOf(100));
        bill.setStatus(BillStatus.UNPAID);
        bill.setGeneratedDate(LocalDate.now());

        when(billRepo.findAll()).thenReturn(List.of(bill));

        List<BillResponse> result = billService.getAllBills();

        assertEquals(1, result.size());
        assertEquals(BigDecimal.valueOf(100), result.get(0).getAmount());
    }

    @Test
    void shouldGetBillById() {
        Bill bill = new Bill();
        bill.setId(1L);
        bill.setAppointment(new Appointment());
        bill.setAmount(BigDecimal.TEN);
        bill.setStatus(BillStatus.UNPAID);
        bill.setGeneratedDate(LocalDate.now());

        when(billRepo.findById(1L)).thenReturn(Optional.of(bill));

        BillResponse result = billService.getBillById(1L);

        assertEquals(BigDecimal.TEN, result.getAmount());
    }

    @Test
    void shouldThrowWhenBillNotFound() {
        when(billRepo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> billService.getBillById(99L));
    }

    @Test
    void shouldPayBillSuccessfully() {
        Bill bill = new Bill();
        bill.setId(1L);
        bill.setAppointment(new Appointment());
        bill.setAmount(BigDecimal.valueOf(150));
        bill.setStatus(BillStatus.UNPAID);
        bill.setGeneratedDate(LocalDate.now());

        PayBillRequest request = new PayBillRequest(1L);

        when(billRepo.findById(1L)).thenReturn(Optional.of(bill));
        when(billRepo.save(any())).thenReturn(bill);

        BillResponse response = billService.payBill(request);

        assertEquals(BillStatus.PAID, response.getStatus());
    }

    @Test
    void shouldThrowWhenPayingAlreadyPaidBill() {
        Bill bill = new Bill();
        bill.setId(1L);
        bill.setStatus(BillStatus.PAID);

        PayBillRequest request = new PayBillRequest(1L);

        when(billRepo.findById(1L)).thenReturn(Optional.of(bill));

        assertThrows(IllegalStateException.class, () -> billService.payBill(request));
    }
}
