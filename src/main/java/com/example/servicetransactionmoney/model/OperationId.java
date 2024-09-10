package com.example.servicetransactionmoney.model;

public class OperationId {
    private String operationId;
    private String code;

    @Override
    public String toString() {
        return "OperationId{" +
                "operationId='" + operationId + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public OperationId(String operationId, String code) {
        this.operationId = operationId;
        this.code = code;
    }

    public OperationId() {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
