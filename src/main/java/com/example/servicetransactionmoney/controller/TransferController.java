package com.example.servicetransactionmoney.controller;


import com.example.servicetransactionmoney.model.Status;
import com.example.servicetransactionmoney.model.Transaction;
import com.example.servicetransactionmoney.model.OperationId;
import com.example.servicetransactionmoney.service.TransferMoneyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.processing.SupportedOptions;

@RestController
public class TransferController implements WebMvcConfigurer {
    private final TransferMoneyService transferMoneyService;

    public TransferController(TransferMoneyService transferMoneyService) {
        this.transferMoneyService = transferMoneyService;
    }


    @PostMapping("/transfer")
    public ResponseEntity<Status> transfer(@Valid @RequestBody Transaction transaction)  {
        Status response = transferMoneyService.transfer(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/confirmOperation")
    public ResponseEntity<String> confirmOperation(OperationId operationId) {
        ResponseEntity<String> tResponseEntity = new ResponseEntity<>(transferMoneyService.confirmOperation(operationId), HttpStatus.OK);
        return tResponseEntity;
    }

}
