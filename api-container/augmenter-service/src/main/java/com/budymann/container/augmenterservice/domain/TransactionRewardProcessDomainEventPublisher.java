package com.budymann.container.augmenterservice.domain;

import com.budymann.container.augmenterserviceapi.events.TransactionRewardProcessDomainEvent;
import io.eventuate.tram.events.aggregates.AbstractAggregateDomainEventPublisher;
import io.eventuate.tram.events.publisher.DomainEventPublisher;

public class TransactionRewardProcessDomainEventPublisher extends AbstractAggregateDomainEventPublisher<TransactionRewardProcess, TransactionRewardProcessDomainEvent> {

    public TransactionRewardProcessDomainEventPublisher(DomainEventPublisher eventPublisher) {
        super(eventPublisher, TransactionRewardProcess.class, TransactionRewardProcess::getId);
    }
}
