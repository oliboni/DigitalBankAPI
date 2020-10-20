package com.example.DigitalBankAPI.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class BadRequestException extends ResponseEntityExceptionHandler {

    public ResponseEntity<Object> badRequest(Exception e){
        ErrorMessage errorMessage = new ErrorMessage(LocalDate.now(), e.getMessage(),"Bad request",400);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
