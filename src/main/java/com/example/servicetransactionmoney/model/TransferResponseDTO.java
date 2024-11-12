package com.example.servicetransactionmoney.model;

public class TransferResponseDTO {
    private String transactionId;

    public TransferResponseDTO(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "TransferResponseDTO{" +
                "transactionId='" + transactionId + '\'' +
                '}';
    }
}
