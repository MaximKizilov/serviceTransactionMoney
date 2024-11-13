package com.example.servicetransactionmoney.controller;


import com.example.servicetransactionmoney.model.ConfirmOperationDTO;
import com.example.servicetransactionmoney.model.Transaction;
import com.example.servicetransactionmoney.model.OperationId;
import com.example.servicetransactionmoney.model.TransferResponseDTO;
import com.example.servicetransactionmoney.service.TransferMoneyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<TransferResponseDTO> transfer(@Validated @RequestBody Transaction transaction) {
            return ResponseEntity.ok(transferMoneyService.transfer(transaction));
        }

    @PostMapping("/confirmOperation")
    public ResponseEntity<ConfirmOperationDTO> confirmOperation(@RequestBody OperationId operationId) {
            return transferMoneyService.confirmOperation(operationId);
        }


}
