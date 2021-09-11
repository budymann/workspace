package com.genericnpc.container.tokenize.service;

import com.genericnpc.container.tokenize.model.Token;
import com.genericnpc.container.tokenize.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;

    public Token tokenize(String attributeName, String attributeValue){
        var tokenFromDB = tokenRepository.findByAttributeNameAndAttributeValue(attributeName, attributeValue);
        if(tokenFromDB != null){
            return tokenFromDB;
        }

        var token=Token.builder()
                .attributeName(attributeName)
                .attributeValue(attributeValue)
                .tokenizedValue(UUID.randomUUID().toString())
                .build();

        return tokenRepository.save(token);
    }
}
