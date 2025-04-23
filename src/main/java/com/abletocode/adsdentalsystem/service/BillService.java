package com.abletocode.adsdentalsystem.service;

import com.abletocode.adsdentalsystem.dto.bill.PayBillRequest;
import com.abletocode.adsdentalsystem.dto.bill.BillResponse;

import java.util.List;

public interface BillService {
    BillResponse payBill(PayBillRequest request);
    List<BillResponse> getAllBills();
    BillResponse getBillById(Long id);

}
