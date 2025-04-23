package com.abletocode.adsdentalsystem.dto.bill;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class PayBillRequest {
    @NotNull
    private Long billId;
}
