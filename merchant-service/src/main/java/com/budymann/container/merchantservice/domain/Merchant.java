package com.budymann.container.merchantservice.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="merchant")
@Access(AccessType.FIELD)
@Getter
@Setter
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MERCHANT_SEQUENCE_GENERATOR")
    @SequenceGenerator(name = "MERCHANT_SEQUENCE_GENERATOR", sequenceName = "MERCHANT_SEQUENCE", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private String merchantName;

}
