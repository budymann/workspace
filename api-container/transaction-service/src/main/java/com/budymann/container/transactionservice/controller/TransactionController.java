package com.budymann.container.transactionservice.controller;

import com.budymann.container.transactionserviceapi.FinancialTransaction;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RestController
public class TransactionController {
    private final EmitterProcessor<Message<FinancialTransaction>> processor = EmitterProcessor.create();

    private FinancialTransaction generateTransaction() {
        List<String> merchantNames = new ArrayList<>();
        merchantNames.add("Energy");
        merchantNames.add("Kmart");
        merchantNames.add("EbGames");

        Random random = new Random();


        return FinancialTransaction.builder()
                .accountNo((100000 + random.nextInt(900000)) + "")
                .amount(BigDecimal.valueOf(random.nextFloat() * 10))
                .merchantName(merchantNames.get(random.nextInt(3))).build();
    }


    @RequestMapping(value={"/transaction"})
    @ResponseBody
    public String getMessage(){
        for(int i = 0; i < 5; i++) {
            var a = MessageBuilder.withPayload(generateTransaction()).build();
            processor.onNext(a);
        }
        return "Sent mock event";
    }

    @Bean
    public Supplier<Flux<Message<FinancialTransaction>>> supplier() {
        return () -> processor;
    }

    @Bean
    public Consumer<Message<FinancialTransaction>> consumer() {
        return input -> {
            System.out.println("CONSUMER 1 SIZE: " + input.getPayload());

        };
    }

}
