package org.sparta.scheduleproject.exception;

import lombok.Getter;
import org.sparta.scheduleproject.StateCode;

@Getter
public class ErrorResponse {
    private int status;
    private String errMsg;

    public ErrorResponse(StateCode stateCode) {
        this.status = stateCode.getStatus();
        this.errMsg = stateCode.getMsg();
    }
}
