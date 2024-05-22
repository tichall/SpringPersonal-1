package org.sparta.scheduleproject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StateCode {
    DELETE_SUCCESS(200, "삭제가 완료되었습니다."),
    PASSWORD_INVALID(401, "비밀번호가 일치하지 않습니다."),
    DELETED_SCHEDULE(404, "이미 삭제된 일정입니다."),
    SCHEDULE_NOT_FOUND(404, "해당 일정을 찾을 수 없습니다.");

    private int status;
    private String msg;
}
