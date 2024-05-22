package org.sparta.scheduleproject.service;

import org.sparta.scheduleproject.dto.ScheduleDeleteRequestDto;
import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResonseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.sparta.scheduleproject.exception.DeletedScheduleAccessException;
import org.sparta.scheduleproject.exception.ErrorCode;
import org.sparta.scheduleproject.exception.PasswordInvalidException;
import org.sparta.scheduleproject.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ScheduleService {
    private static final String DELETE_SUCCESS = "삭제 성공!";
    private static final String PASSWORD_INVALID = "비밀번호가 일치하지 않습니다.";

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
        if (checkPassword(requestDto.getPassword(), schedule)) {
            schedule.update(requestDto);
            scheduleRepository.flush();
            return new ScheduleResonseDto(schedule);
        } else {
            throw new IllegalArgumentException(PASSWORD_INVALID);
        }
    }

    @Transactional
    public String deleteSchedule(ScheduleDeleteRequestDto requestDto) {
        Schedule schedule = findSchedule(requestDto.getId());
        if (checkPassword(requestDto.getPassword(), schedule)) {
            scheduleRepository.delete(schedule);
            return DELETE_SUCCESS;
        } else {
            throw new PasswordInvalidException("비밀번호 오류", ErrorCode.PASSWORD_INVALID);
        }
    }

    private Schedule findSchedule(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);

        return scheduleRepository.findById(id).orElseThrow(() -> {
            if (id < scheduleRepository.findTopByOrderByIdDesc().getId()) {
                throw new DeletedScheduleAccessException("삭제된 일정 접근", ErrorCode.DELETED_SCHEDULE);
            }
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다!");
        });
    }

    /**
     * 비밀번호 일치 여부 확인
     * @param InputPassword 입력한 비밀번호
     * @param schedule 비밀번호 비교할 일정 객체
     * @return 비밀번호 일치 -> true, 불일치 -> false
     *
     */
    private boolean checkPassword(String InputPassword, Schedule schedule) {
        return Objects.equals(InputPassword, schedule.getPassword());
    }
}
