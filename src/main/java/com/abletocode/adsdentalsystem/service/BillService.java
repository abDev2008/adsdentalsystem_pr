package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.dto.bill.PayBillRequest;
import com.abletocode.adsdentalsystem.dto.bill.BillResponse;

public interface BillService {
    BillResponse payBill(PayBillRequest request);
}
