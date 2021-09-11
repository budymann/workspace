package com.genericnpc.container.tokenize.controller;

import com.budymann.container.tokenizeserviceapiweb.TokenRequest;
import com.budymann.container.tokenizeserviceapiweb.TokenResponse;
import com.genericnpc.container.tokenize.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value={"/token"})
public class TokenizeController {
    @Autowired
    TokenService tokenService;

    @PostMapping()
    public ResponseEntity<TokenResponse> tokenize(@RequestBody TokenRequest tokenRequestDto){
        if(tokenRequestDto.getAttributeName().equalsIgnoreCase("failme")){
            throw new RuntimeException("Get out!");
        }

        var token = tokenService.tokenize(tokenRequestDto.attributeName, tokenRequestDto.attributeValue);
        var tokenResponseDto =  TokenResponse.builder()
                .attributeName(token.getAttributeName())
                .attributeValue(token.getAttributeValue())
                .tokenizedValue(token.getTokenizedValue())
                .build();

        return new ResponseEntity<>(tokenResponseDto, HttpStatus.OK);
    }
}
