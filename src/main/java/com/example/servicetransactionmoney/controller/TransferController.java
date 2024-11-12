package com.example.servicetransactionmoney.controller;


import com.example.servicetransactionmoney.model.Status;
import com.example.servicetransactionmoney.model.Transaction;
import com.example.servicetransactionmoney.model.OperationId;
import com.example.servicetransactionmoney.model.TransferResponseDTO;
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
    public ResponseEntity<TransferResponseDTO> transfer(@Valid @RequestBody Transaction transaction) {
        try {
            return new ResponseEntity<>(transferMoneyService.transfer(transaction), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/confirmOperation")
    public ResponseEntity<String> confirmOperation(OperationId operationId) {
        try {
            return new ResponseEntity<>(transferMoneyService.confirmOperation(operationId), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
