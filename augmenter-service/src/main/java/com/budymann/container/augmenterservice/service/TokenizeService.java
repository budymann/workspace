package com.budymann.container.augmenterservice.service;

import com.budymann.container.tokenizeserviceapiweb.TokenRequest;
import com.budymann.container.tokenizeserviceapiweb.TokenResponse;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class TokenizeService {

    @Value("${application.tokenize.service.url}")
    public String tokenizeServiceUrl;

    public RestTemplate restTemplate = new RestTemplate();

    @Retryable( value = Exception.class,
            maxAttempts = 10, backoff = @Backoff(delay = 100))
    public TokenResponse getTokenize(TokenRequest tokenRequest) {
        System.out.println(Thread.currentThread().getName());
        var response = restTemplate.postForEntity(tokenizeServiceUrl, tokenRequest, TokenResponse.class);
        return response.getBody();
    }

    public List<TokenResponse> getTokenizeParallel(List<TokenRequest> tokenRequestList) {
        var futures = new ArrayList<CompletableFuture<TokenResponse>>();

        for (TokenRequest tokenRequest : tokenRequestList) {
            futures.add(CompletableFuture.supplyAsync(() -> getTokenize(tokenRequest)));
        }

        var tokenResponses = new ArrayList<TokenResponse>();

        for (CompletableFuture<TokenResponse> future : futures) {
            try {
                tokenResponses.add(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return tokenResponses;
    }

    public List<TokenResponse> getTokenizeSynchronous(List<TokenRequest> tokenRequestList) {
        var tokenResponses = new ArrayList<TokenResponse>();
        for (TokenRequest tokenRequest : tokenRequestList) {
            tokenResponses.add(getTokenize(tokenRequest));
        }

        return tokenResponses;
    }
}
