package com.example.servicetransactionmoney.service;


import com.example.servicetransactionmoney.logger.TransactionLogger;
import com.example.servicetransactionmoney.model.OperationId;
import com.example.servicetransactionmoney.model.Transaction;
import com.example.servicetransactionmoney.model.TransferResponseDTO;
import com.example.servicetransactionmoney.repository.CompletedTransactionRepo;
import com.example.servicetransactionmoney.repository.FailedTransactionRepo;
import com.example.servicetransactionmoney.repository.ProcessingTransactionRepo;
import com.example.servicetransactionmoney.utils.OperationIDGenerator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferMoneyService {

    private final OperationIDGenerator operationIDGenerator;
    private final ProcessingTransactionRepo processingTransactionRepo;
    private final CompletedTransactionRepo completedTransactionRepo;
    private final FailedTransactionRepo failedTransactionRepo;

    public TransferMoneyService(OperationIDGenerator operationIDGenerator,
                                ProcessingTransactionRepo processingTransactionRepo,
                                CompletedTransactionRepo completedTransactionRepo,
                                FailedTransactionRepo failedTransactionRepo) {

        this.operationIDGenerator = operationIDGenerator;
        this.processingTransactionRepo = processingTransactionRepo;
        this.completedTransactionRepo = completedTransactionRepo;
        this.failedTransactionRepo = failedTransactionRepo;
    }

    public TransferResponseDTO transfer (Transaction transaction) {
        String operationID = operationIDGenerator.generateOperationID();
        transaction.getId().setOperationId(operationID);
        processingTransactionRepo.add(transaction);
        TransactionLogger.logProcessedTrans(transaction, TransactionLogger.LogType.PENDING);
        return transferMoneyRepo.addTransaction(transaction);
    }

    public String confirmOperation(OperationId operationId) {
        String operationID = operationId.getOperationId();
        String codeRequest = operationId.getCode();

        Optional<Transaction> transactionToConfirm = processingTransactionRepo.get(operationID);
        if (transactionToConfirm.isPresent()) {
            Transaction transaction = transactionToConfirm.get();
            processingTransactionRepo.delete(transactionToConfirm.get());
            if(transactionToConfirm.get().getId().getCode().equals(code)){
                completedTransactionRepo.add(transaction);
            }
        }
        return transferMoneyRepo.saveConfirmationStatus(operationId);
    }

}
