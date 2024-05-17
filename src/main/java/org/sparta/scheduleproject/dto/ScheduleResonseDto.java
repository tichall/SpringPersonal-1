package org.sparta.scheduleproject.dto;

import lombok.Getter;
import org.sparta.scheduleproject.entity.Schedule;

import java.time.LocalDateTime;

/**
 * 일정 조회 시 반환할 responseDto
 */
@Getter
public class ScheduleResonseDto {
    private Long id;
    private String title;
    private String contents;
    private String owner;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public ScheduleResonseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.owner = schedule.getOwner();
        this.createdAt = schedule.getCreatedAt();
        this.modifiedAt = schedule.getModifiedAt();
    }

}
