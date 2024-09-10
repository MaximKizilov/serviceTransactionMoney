package com.example.servicetransactionmoney.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record Amount(@NotBlank @Size(min = 2) String currency, @Positive Long value) {
    @Override
    public String toString() {
        return "Amount{" +
                "currency='" + currency + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public String currency() {
        return currency;
    }

    @Override
    public Long value() {
        return value;
    }
}
