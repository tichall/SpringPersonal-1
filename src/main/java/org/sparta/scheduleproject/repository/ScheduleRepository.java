package org.sparta.scheduleproject.repository;

import org.sparta.scheduleproject.dto.ScheduleRequestDto;
import org.sparta.scheduleproject.dto.ScheduleResonseDto;
import org.sparta.scheduleproject.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByCreatedAtDesc();

}
