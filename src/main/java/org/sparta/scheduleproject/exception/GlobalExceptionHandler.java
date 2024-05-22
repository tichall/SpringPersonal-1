package org.sparta.scheduleproject.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorResponse> handlePasswordInvalidException(PasswordInvalidException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getStateCode());
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(e.getStateCode().getStatus()));
    }

    @ExceptionHandler(DeletedScheduleAccessException.class)
    public ResponseEntity<ErrorResponse> handleDeletedScheduleAccessException (DeletedScheduleAccessException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getStateCode());
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(e.getStateCode().getStatus()));
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleScheduleNotFoundException (ScheduleNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getStateCode());
        return new ResponseEntity<>(errorResponse, HttpStatusCode.valueOf(e.getStateCode().getStatus()));
    }
}
