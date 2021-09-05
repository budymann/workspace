package com.budymann.container.transactionserviceapi;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialTransaction {
    private String merchantName;
    private BigDecimal amount;
    private String accountNo;
}
