package com.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String errorCode, String message, int status, Instant timestamp) {
    public static ErrorResponse of(String errorCode, String message, HttpStatus status) {
        return new ErrorResponse(errorCode, message, status.value(), Instant.now());
    }
}
