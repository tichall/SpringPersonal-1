package org.sparta.scheduleproject.exception;

import lombok.Getter;
import org.sparta.scheduleproject.StateCode;

@Getter
public class ScheduleNotFoundException extends RuntimeException {
    private StateCode stateCode;

    public ScheduleNotFoundException (String msg, StateCode stateCode) {
        super(msg);
        this.stateCode = stateCode;
    }
}
