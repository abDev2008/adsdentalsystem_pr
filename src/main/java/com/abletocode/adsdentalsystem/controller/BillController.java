package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.dto.bill.BillResponse;
import com.abletocode.adsdentalsystem.dto.bill.PayBillRequest;
import com.abletocode.adsdentalsystem.service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/pay")
    public ResponseEntity<BillResponse> pay(@RequestBody @Valid PayBillRequest request) {
        BillResponse response = billService.payBill(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
