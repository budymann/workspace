package com.budymann.container.augmenterservice.domain;

import com.budymann.container.augmenterserviceapi.events.TransactionRewardProcessDetails;
import com.budymann.container.augmenterserviceapi.events.TransactionRewardProcessDomainEvent;
import com.budymann.container.augmenterserviceapi.events.TransactionRewardProcessStarted;
import com.budymann.container.augmenterserviceapi.events.TransactionRewardProcessState;
import com.budymann.container.transactionserviceapi.FinancialTransaction;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static java.util.Collections.singletonList;

@Entity
@Table(name = "TransactionRewardProcess")
@Access(AccessType.FIELD)
@Getter
@Setter
public class TransactionRewardProcess {
    @Id
    @GeneratedValue
    private Long id;

    private String transactionId;
    private String merchantName;
    private BigDecimal amount;
    private String accountNo;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private TransactionRewardProcessState state;

    public TransactionRewardProcess(String transactionId, String merchantName, BigDecimal amount, String accountNo){
        this.transactionId = transactionId;
        this.merchantName = merchantName;
        this.amount = amount;
        this.accountNo = accountNo;
        this.state = TransactionRewardProcessState.PROCESSING;
    }

    public static ResultWithDomainEvents<TransactionRewardProcess, TransactionRewardProcessDomainEvent> startTransactionRewardProcess(String transactionId, String merchantName, BigDecimal amount, String accountNo) {
        TransactionRewardProcess order = new TransactionRewardProcess(transactionId, merchantName, amount, accountNo);
        List<TransactionRewardProcessDomainEvent> events = singletonList(new TransactionRewardProcessStarted(
                new TransactionRewardProcessDetails(transactionId, merchantName, amount, accountNo)));
        return new ResultWithDomainEvents<>(order, events);
    }

}
