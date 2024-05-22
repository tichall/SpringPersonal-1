package org.sparta.scheduleproject.service;

import org.sparta.scheduleproject.dto.ScheduleDeleteRequestDto;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResonseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.exception.DeletedScheduleAccessException;
import org.sparta.scheduleproject.StateCode;
import org.sparta.scheduleproject.exception.PasswordInvalidException;
import org.sparta.scheduleproject.exception.ScheduleNotFoundException;
import org.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ScheduleService {
    private static final String DELETE_SUCCESS = "삭제 성공!";

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
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
        checkPassword(requestDto.getPassword(), schedule);
        schedule.update(requestDto);
        scheduleRepository.flush();
        return new ScheduleResonseDto(schedule);
    }

    @Transactional
    public String deleteSchedule(Long id, ScheduleDeleteRequestDto requestDto) {
        Schedule schedule = findSchedule(id);
        checkPassword(requestDto.getPassword(), schedule);
        scheduleRepository.delete(schedule);
        return DELETE_SUCCESS; // String이 아니라 ResponseEntity 반환으로 바꿀 수는 없을까..?
    }

    private Schedule findSchedule(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);

        return scheduleRepository.findById(id).orElseThrow(() -> {
            if (id < scheduleRepository.findTopByOrderByIdDesc().getId()) {
                throw new DeletedScheduleAccessException("삭제된 일정 접근", StateCode.DELETED_SCHEDULE);
            }
            throw new ScheduleNotFoundException("선택한 일정은 존재하지 않습니다!", StateCode.SCHEDULE_NOT_FOUND);
        });
    }

    /**
     * 비밀번호 일치 여부 확인
     * @param InputPassword 입력한 비밀번호
     * @param schedule 비밀번호 비교할 일정 객체
     * @return 비밀번호 일치 -> true, 불일치 -> false
     *
     */
    private void checkPassword(String InputPassword, Schedule schedule) {
        if (!Objects.equals(InputPassword, schedule.getPassword())) {
            throw new PasswordInvalidException("비밀번호 오류", StateCode.PASSWORD_INVALID);
        }
    }
}
