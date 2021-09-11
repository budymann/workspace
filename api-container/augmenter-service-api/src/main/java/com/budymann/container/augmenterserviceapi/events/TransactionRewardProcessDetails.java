package com.budymann.container.augmenterserviceapi.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRewardProcessDetails {
    private String transactionId;
    private String merchantName;
    private BigDecimal amount;
    private String accountNo;
}
