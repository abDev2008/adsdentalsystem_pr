package com.abletocode.adsdentalsystem.dto.bill;

import com.abletocode.adsdentalsystem.domain.enums.BillStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class BillResponse {
    private Long id;
    private Long appointmentId;
    private BigDecimal amount;
    private BillStatus status;
    private LocalDate generatedDate;
}
