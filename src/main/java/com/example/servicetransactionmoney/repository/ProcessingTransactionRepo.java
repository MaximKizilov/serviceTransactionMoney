package com.example.servicetransactionmoney.repository;

import com.example.servicetransactionmoney.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ProcessingTransactionRepo {
private final List<Transaction> processingTransactions = new CopyOnWriteArrayList<>();
    public void add(Transaction transaction) {

    }

    public Optional<Transaction> get(String id) {
       return processingTransactions.stream().filter(transaction -> transaction.getId().equals(id)).findFirst();
    }

    public void delete(Transaction transaction) {
        processingTransactions.remove(transaction);

    }
}
