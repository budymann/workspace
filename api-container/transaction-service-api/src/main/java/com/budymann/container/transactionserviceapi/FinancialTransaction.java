package com.budymann.container.transactionserviceapi;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialTransaction {
    private String transactionId;
    private String merchantName;
    private BigDecimal amount;
    private String accountNo;
}
