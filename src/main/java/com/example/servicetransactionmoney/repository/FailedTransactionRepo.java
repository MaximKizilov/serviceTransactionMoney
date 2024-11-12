package com.example.servicetransactionmoney.repository;

import com.example.servicetransactionmoney.model.OperationId;
import com.example.servicetransactionmoney.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class FailedTransactionRepo {
    private final List<Transaction> failedTransactions = new CopyOnWriteArrayList<>();
    void add(Transaction transaction) {
        failedTransactions.add(transaction);
    }
}
