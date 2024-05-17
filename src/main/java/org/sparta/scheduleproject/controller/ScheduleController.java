package org.sparta.scheduleproject.controller;

import org.sparta.scheduleproject.dto.ScheduleDeleteRequestDto;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResonseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedule")
    public ScheduleResonseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    @GetMapping("/schedules")
    public List<ScheduleResonseDto> getSchedules() {
        return scheduleService.getSchedules();
    }

    @GetMapping("/schedule/{id}")
    public ScheduleResonseDto getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id);
    }

    @PutMapping("/schedule/{id}")
    public ScheduleResonseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(id, requestDto);
    }

    @DeleteMapping("/schedule")
    public String deleteSchedule(@RequestBody ScheduleDeleteRequestDto requestDto) {
        return scheduleService.deleteSchedule(requestDto);
    }
}
