package com.example.servicetransactionmoney.service;


import com.example.servicetransactionmoney.logger.TransactionLogger;
import com.example.servicetransactionmoney.model.ConfirmOperationDTO;
import com.example.servicetransactionmoney.model.OperationId;
import com.example.servicetransactionmoney.model.Transaction;
import com.example.servicetransactionmoney.model.TransferResponseDTO;
import com.example.servicetransactionmoney.repository.CompletedTransactionRepo;
import com.example.servicetransactionmoney.repository.FailedTransactionRepo;
import com.example.servicetransactionmoney.repository.ProcessingTransactionRepo;
import com.example.servicetransactionmoney.utils.OperationIDGenerator;
import com.example.servicetransactionmoney.utils.ControllerAdvices;
import org.springframework.http.ResponseEntity;
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
        transaction.getOperationId().setId(operationID);
        processingTransactionRepo.add(transaction);
        TransactionLogger.logProcessedTrans(transaction, TransactionLogger.LogType.PENDING);
        return new TransferResponseDTO(operationID);
    }

    public ResponseEntity <ConfirmOperationDTO> confirmOperation(OperationId operationId) {
        String operationID = operationId.getId();
        String codeRequest = operationId.getCode();

        Optional<Transaction> transactionToConfirm = processingTransactionRepo.get(operationID);
        if (transactionToConfirm.isPresent()) {
            Transaction transaction = transactionToConfirm.get();
            processingTransactionRepo.delete(transactionToConfirm.get());
            if(transactionToConfirm.get().getOperationId().getCode().equals(codeRequest)){
                completedTransactionRepo.add(transaction);
                TransactionLogger.logProcessedTrans(transaction, TransactionLogger.LogType.SUCCESS);
                return ResponseEntity.ok(new ConfirmOperationDTO(operationID, ControllerAdvices.MSG_200));
            }else{
                failedTransactionRepo.add(transaction);
                TransactionLogger.logProcessedTrans(transaction, TransactionLogger.LogType.FAILED);
                return ResponseEntity.internalServerError().body(new ConfirmOperationDTO(operationID, ControllerAdvices.MSG_500));
            }
        }else {
            TransactionLogger.logUnknown(TransactionLogger.LogType.UNKNOWN);
            return ResponseEntity.badRequest().body(new ConfirmOperationDTO(operationID, ControllerAdvices.MSG_400));
        }

    }

}
