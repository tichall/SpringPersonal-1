package org.sparta.scheduleproject.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String errMsg;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.errMsg = errorCode.getErrMsg();
    }
}