package com.example.servicetransactionmoney.utils;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OperationIDGenerator {
    private final Set<String> usedOperID = ConcurrentHashMap.newKeySet();

    public String generateOperationID() {
        String operationID;
        Random random = new Random();
        do{
            operationID = String.valueOf(1000+random.nextInt(9000));
        }while(usedOperID.contains(operationID));
        usedOperID.add(operationID);
        return operationID;
    }

}
