package com.example.servicetransactionmoney.repository;

import com.example.servicetransactionmoney.model.OperationId;
import com.example.servicetransactionmoney.model.Status;
import com.example.servicetransactionmoney.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TransferMoneyRepo {
    //id
private final Set <String> id = ConcurrentHashMap.newKeySet();
//транзакция
private final List<Transaction> transactions = new CopyOnWriteArrayList<>();
//статус
private final List<OperationId> confirmationStatus = new CopyOnWriteArrayList<>();

public Status addTransaction(Transaction transaction) {
    id.add(transaction.getId());
    transactions.add(transaction);
    return new Status(transaction.getId(), "Operation Transfer!");
}
public String saveConfirmationStatus(OperationId operationId) {
    confirmationStatus.add(operationId);
    return String.valueOf(UUID.randomUUID());
}
}
