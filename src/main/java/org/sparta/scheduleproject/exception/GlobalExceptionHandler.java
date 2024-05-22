package org.sparta.scheduleproject.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorResponse> handlePasswordInvalidException(PasswordInvalidException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(e.getErrorCode().getStatus()));
    }

}
