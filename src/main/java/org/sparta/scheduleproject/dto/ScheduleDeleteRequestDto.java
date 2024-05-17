package org.sparta.scheduleproject.dto;

import lombok.Getter;

/**
 * 일정 삭제 시 받아올 RequestDto
 */
@Getter
public class ScheduleDeleteRequestDto {
    private Long id;
    private String password;
}
