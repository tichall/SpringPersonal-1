package org.sparta.scheduleproject.exception;

import lombok.Getter;
import org.sparta.scheduleproject.StateCode;

@Getter
public class PasswordInvalidException extends RuntimeException {
    private StateCode stateCode;
    public PasswordInvalidException(String msg, StateCode stateCode) {
        super(msg);
        this.stateCode = stateCode;
    }
}
