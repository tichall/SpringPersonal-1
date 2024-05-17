package org.sparta.scheduleproject.dto;

import lombok.Getter;

/**
 * 일정 생성, 수정 시 받아올 RequestDto
 */
@Getter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private String owner;
    private String password;
}
