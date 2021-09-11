package com.budymann.container.transactionserviceapi.messaging;

import com.budymann.container.transactionserviceapi.FinancialTransaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class FinancialTransactionDeserializer implements Deserializer {
    @Override
    public void configure(Map configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Object deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        FinancialTransaction financialTransaction = null;
        try {
            financialTransaction = mapper.readValue(bytes, FinancialTransaction.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return financialTransaction;
    }

    @Override
    public Object deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
