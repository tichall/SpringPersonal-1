package org.sparta.scheduleproject.exception;

import lombok.Getter;

@Getter
public class DeletedScheduleAccessException extends RuntimeException {
    private ErrorCode errorCode;

    public DeletedScheduleAccessException (String msg, ErrorCode errorCode) {
        super(msg); // msg를 꼭 매개변수로 받을 필요가 있는지 궁금.. 어차피 사용되지 않는 값..?
        this.errorCode = errorCode;
    }
}
