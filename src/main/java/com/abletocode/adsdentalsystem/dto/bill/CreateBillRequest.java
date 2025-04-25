package com.abletocode.adsdentalsystem.dto.bill;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CreateBillRequest {

    @NotNull
    private Long appointmentId;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal amount;

    @NotNull
    private LocalDate issuedDate;
}
