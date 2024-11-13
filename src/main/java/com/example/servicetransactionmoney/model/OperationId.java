package com.example.servicetransactionmoney.model;

import jakarta.validation.constraints.Size;

public class OperationId {
    @Size(max = 4, min = 4)
    private String id;
    private String code = "0000";

    @Override
    public String toString() {
        return "OperationId{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
