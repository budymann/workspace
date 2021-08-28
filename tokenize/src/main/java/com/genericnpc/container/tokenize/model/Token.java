package com.genericnpc.container.tokenize.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "Token")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TOKEN_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="TOKEN_SEQUENCE_GENERATOR", sequenceName="TOKEN_SEQUENCE", initialValue=1, allocationSize=10)
    @Column(name="TOKEN_ID")
    public Long Id;
    public String attributeName;
    public String attributeValue;
    public String tokenizedValue;
}
