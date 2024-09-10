package com.example.servicetransactionmoney;

import org.springframework.boot.SpringApplication;

public class TestServiceTransactionMoneyApplication {

    public static void main(String[] args) {
        SpringApplication.from(ServiceTransactionMoneyApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
