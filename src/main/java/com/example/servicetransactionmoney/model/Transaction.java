package com.example.servicetransactionmoney.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Transaction {
    @NotBlank
    @Size(min = 3, max = 3)
    @Pattern(regexp = "^\\d{3}$",message = "Incorrect date")
    private String cardFromCVV;

    @NotBlank
    @Size(min = 16, max = 16)
    @Pattern(regexp = "^\\d{16}$",message = "Incorrect date")
    private String cardFromNumber;

    @NotBlank
    @Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{2}$",message = "Incorrect date")
    private String cardFromValidTill;

    @NotBlank
    @Size(min = 16, max = 16)
    @Pattern(regexp = "^\\d{16}$",message = "Incorrect date")
    private String cardToNumber;

    @Valid
    private Amount amount;
    private String id;

    public @NotBlank @Size(min = 3, max = 3) @Pattern(regexp = "^\\d{3}$", message = "Incorrect date") String getCardFromCVV() {
        return cardFromCVV;
    }

    public void setCardFromCVV(@NotBlank @Size(min = 3, max = 3) @Pattern(regexp = "^\\d{3}$", message = "Incorrect date") String cardFromCVV) {
        this.cardFromCVV = cardFromCVV;
    }

    public @NotBlank @Size(min = 16, max = 16) @Pattern(regexp = "^\\d{16}$", message = "Incorrect date") String getCardFromNumber() {
        return cardFromNumber;
    }

    public void setCardFromNumber(@NotBlank @Size(min = 16, max = 16) @Pattern(regexp = "^\\d{16}$", message = "Incorrect date") String cardFromNumber) {
        this.cardFromNumber = cardFromNumber;
    }

    public @NotBlank @Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{2}$", message = "Incorrect date") String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public void setCardFromValidTill(@NotBlank @Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{2}$", message = "Incorrect date") String cardFromValidTill) {
        this.cardFromValidTill = cardFromValidTill;
    }

    public @NotBlank @Size(min = 16, max = 16) @Pattern(regexp = "^\\d{16}$", message = "Incorrect date") String getCardToNumber() {
        return cardToNumber;
    }

    public void setCardToNumber(@NotBlank @Size(min = 16, max = 16) @Pattern(regexp = "^\\d{16}$", message = "Incorrect date") String cardToNumber) {
        this.cardToNumber = cardToNumber;
    }

    public @Valid Amount getAmount() {
        return amount;
    }

    public void setAmount(@Valid Amount amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "cardFromCVV='" + cardFromCVV + '\'' +
                ", cardFromNumber='" + cardFromNumber + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", amount=" + amount +
                ", operationID='" + id + '\'' +
                '}';
    }
}
