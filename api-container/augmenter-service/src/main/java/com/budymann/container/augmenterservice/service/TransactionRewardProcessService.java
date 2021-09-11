package com.budymann.container.augmenterservice.service;

import com.budymann.container.augmenterservice.domain.TransactionRewardProcess;
import com.budymann.container.augmenterservice.domain.TransactionRewardProcessDomainEventPublisher;
import com.budymann.container.augmenterservice.domain.TransactionRewardProcessRepository;
import com.budymann.container.augmenterserviceapi.events.TransactionRewardProcessDomainEvent;
import io.eventuate.tram.events.aggregates.ResultWithDomainEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


public class TransactionRewardProcessService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private TransactionRewardProcessRepository transactionRewardProcessRepository;
    private TransactionRewardProcessDomainEventPublisher transactionRewardProcessDomainEventPublisher;

    public TransactionRewardProcessService(TransactionRewardProcessRepository transactionRewardProcessRepository,
                                           TransactionRewardProcessDomainEventPublisher transactionRewardProcessDomainEventPublisher){
        this.transactionRewardProcessRepository = transactionRewardProcessRepository;
        this.transactionRewardProcessDomainEventPublisher = transactionRewardProcessDomainEventPublisher;
    }

    @Transactional
    public TransactionRewardProcess startTransactionRewardProcess(String transactionId, String merchantName, BigDecimal amount, String accountNo){
        ResultWithDomainEvents<TransactionRewardProcess, TransactionRewardProcessDomainEvent> transactionRewardProcessAndEvents =
                TransactionRewardProcess.startTransactionRewardProcess(transactionId, merchantName, amount, accountNo);

        TransactionRewardProcess transactionRewardProcess = transactionRewardProcessAndEvents.result;
        transactionRewardProcessRepository.save(transactionRewardProcess);

        transactionRewardProcessDomainEventPublisher.publish(transactionRewardProcess, transactionRewardProcessAndEvents.events);

        return transactionRewardProcess;
    }

}
