package com.example.DigitalBankAPI.exceptions;

import java.time.LocalDate;

public class ErrorMessage {
    private LocalDate date;
    private Integer status;
    private String error;
    private String message;

    public ErrorMessage() {}

    public ErrorMessage(LocalDate date, String message, String error, Integer status) {
        this.date = date;
        this.message = message;
        this.error = error;
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public Integer getStatus() {
        return status;
    }
}
