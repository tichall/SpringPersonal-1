package org.sparta.scheduleproject.exception;

import lombok.Getter;
import org.sparta.scheduleproject.StateCode;

@Getter
public class DeletedScheduleAccessException extends RuntimeException {
    private StateCode stateCode;

    public DeletedScheduleAccessException (String msg, StateCode stateCode) {
        super(msg); // msg를 꼭 매개변수로 받을 필요가 있는지 궁금.. 어차피 사용되지 않는 값..?
        this.stateCode = stateCode;
    }
}
