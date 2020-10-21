package com.example.DigitalBankAPI.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.LocalDate;

@ControllerAdvice
public class IllegalRequestException {
    public ResponseEntity<Object> illegalState(IllegalStateException e){
        ErrorMessage errorMessage = new ErrorMessage(LocalDate.now(), e.getMessage(),"Unprocessable Entity",422);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> illegalState(String msg){
        ErrorMessage errorMessage = new ErrorMessage(LocalDate.now(), msg,"Unprocessable Entity",422);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    public ResponseEntity<Object> illegalArgument(IllegalArgumentException e){
        ErrorMessage errorMessage = new ErrorMessage(LocalDate.now(), e.getMessage(),"Unprocessable Entity",422);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
