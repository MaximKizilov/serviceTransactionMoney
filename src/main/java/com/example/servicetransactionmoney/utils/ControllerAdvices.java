package com.example.servicetransactionmoney.utils;

import com.example.servicetransactionmoney.model.ConfirmOperationDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvices {

    public static final String MSG_200 = "Success confirmation";
    public static final String MSG_400 = "Error input data";
    public static final String MSG_500 = "Error confirmation";



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ConfirmOperationDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>(new ConfirmOperationDTO(MSG_400), HttpStatus.BAD_REQUEST);
    }
}
