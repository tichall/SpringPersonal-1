package org.sparta.scheduleproject.exception;

import lombok.Getter;

@Getter
public class PasswordInvalidException extends RuntimeException {
    private ErrorCode errorCode;
    public PasswordInvalidException(String msg, ErrorCode errorCode) {
        super(msg);
        this.errorCode = errorCode;
    }
}
