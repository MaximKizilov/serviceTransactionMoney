package com.example.servicetransactionmoney.model;

public class ConfirmOperationDTO {
    private String id;
    private String message;

    public ConfirmOperationDTO(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public ConfirmOperationDTO(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ConfirmOperationDTO{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
