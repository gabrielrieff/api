package com.exception;

import org.springframework.http.HttpStatus;

public class GenericException extends BaseException {
    public GenericException(String message, HttpStatus status, String errorCode) {
        super(message, status, errorCode);
    }
}