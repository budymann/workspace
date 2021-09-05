package com.budymann.container.augmenterservice.domain;

import com.budymann.container.augmenterserviceapi.events.TransactionRewardProcessState;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
public class TransactionRewardProcess {
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Enumerated(EnumType.STRING)
    private TransactionRewardProcessState state;

    private Long transactionId;

}
