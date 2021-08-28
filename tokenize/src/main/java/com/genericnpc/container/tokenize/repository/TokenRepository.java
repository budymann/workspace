package com.genericnpc.container.tokenize.repository;

import com.genericnpc.container.tokenize.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByAttributeNameAndAttributeValue(String attributeName, String attributeValue);
}
