package org.sparta.scheduleproject.service;

import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResonseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResonseDto createSchedule(ScheduleRequestDto requestDto) {
        Schedule schedule = new Schedule(requestDto);
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return new ScheduleResonseDto(saveSchedule);
    }

    public List<ScheduleResonseDto> getSchedules() {
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResonseDto::new).toList();
    }

    public ScheduleResonseDto getScheduleById(Long id) {
        Schedule schedule = findSchedule(id);
        return new ScheduleResonseDto(schedule);
    }

    @Transactional
    public ScheduleResonseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
        if (checkPassword(requestDto, schedule)) {
            schedule.update(requestDto);
            scheduleRepository.flush();
        }
        return new ScheduleResonseDto(schedule);
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다!")
        );
    }

    private boolean checkPassword(ScheduleRequestDto requestDto, Schedule schedule) {
        if (requestDto.getPassword().equals(schedule.getPassword())) {
            return true;
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다!");
        }
    }
}
