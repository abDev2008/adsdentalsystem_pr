package com.abletocode.adsdentalsystem.controller;

import com.abletocode.adsdentalsystem.dto.bill.BillResponse;
import com.abletocode.adsdentalsystem.dto.bill.PayBillRequest;
import com.abletocode.adsdentalsystem.service.BillService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<BillResponse>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillResponse> getBillById(@PathVariable Long id) {
        return ResponseEntity.ok(billService.getBillById(id));
    }

}
