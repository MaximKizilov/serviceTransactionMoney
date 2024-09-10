package com.example.servicetransactionmoney.service;


import com.example.servicetransactionmoney.model.OperationId;
import com.example.servicetransactionmoney.model.Status;
import com.example.servicetransactionmoney.model.Transaction;
import com.example.servicetransactionmoney.repository.TransferMoneyRepo;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.UUID;

@Service
public class TransferMoneyService {
private final TransferMoneyRepo transferMoneyRepo;

    public TransferMoneyService(TransferMoneyRepo transferMoneyRepo) {
        this.transferMoneyRepo = transferMoneyRepo;
    }
    public Status transfer (Transaction transaction) {
        transaction.setId(UUID.randomUUID().toString());
        return transferMoneyRepo.addTransaction(transaction);
    }
    public String confirmOperation(OperationId operationId) {
        return transferMoneyRepo.saveConfirmationStatus(operationId);
    }

}
