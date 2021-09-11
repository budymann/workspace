package com.budymann.container.augmenterservice.web.controller;

import com.budymann.container.augmenterservice.service.TokenizeService;
import com.budymann.container.tokenizeserviceapiweb.TokenRequest;
import com.budymann.container.tokenizeserviceapiweb.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value={"/augment"})
public class AugmenterController {

    @Autowired
    public TokenizeService tokenizeService;

    @RequestMapping(value="/single")
    @GetMapping
    public TokenResponse tokenize() throws Exception {
        var tokenRequest = new TokenRequest();
        tokenRequest.setAttributeName("failme");
        tokenRequest.setAttributeValue(UUID.randomUUID().toString());
        var token = tokenizeService.getTokenize(tokenRequest);
        return token;
    }

    @RequestMapping(value="/synchronous")
    @GetMapping
    public List<TokenResponse> getTokenizeSynchronous(int amount) throws Exception {
        var tokens = tokenizeService.getTokenizeSynchronous(getRandomTokenRequests(amount, true));
        return tokens;
    }

    @RequestMapping(value="/parallel")
    @GetMapping
    public List<TokenResponse> getTokenizeParallel(int amount){
        var tokens = tokenizeService.getTokenizeParallel(getRandomTokenRequests(amount, true));
        return tokens;
    }


    private List<TokenRequest> getRandomTokenRequests(int amount, boolean shouldFail){
        var tokenRequests = new ArrayList<TokenRequest>();
        for(var i = 0; i < amount; i++) {
            tokenRequests.add(new TokenRequest("TransactionId", UUID.randomUUID().toString()));
        }

        if(shouldFail){
            tokenRequests.add(new TokenRequest("failme", UUID.randomUUID().toString()));
        }

        return tokenRequests;
    }
}
