package com.example.servicetransactionmoney.model;

public class Status {
    private String operationId;
    private String verificationCode = "0000";


    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Status(String operationId, String verificationCode) {
        this.operationId = operationId;
        this.verificationCode = verificationCode;
    }
    public Status() {

    }
}
