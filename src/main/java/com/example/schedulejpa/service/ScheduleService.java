package com.example.schedulejpa.service;

import com.example.schedulejpa.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpa.entity.Schedule;
import com.example.schedulejpa.entity.User;
import com.example.schedulejpa.repository.ScheduleRepository;
import com.example.schedulejpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // todo 일정 등록
    public ScheduleResponseDto save(String title, String contents, LocalDateTime startTime, LocalDateTime endTime) {

        // todo 로그인 기능이랑 연동 필요
        User findUser = userRepository.findUserByNameOrElseThrow(name);

        Schedule schedule = new Schedule(title, contents, startTime, endTime);
        user.setUser(findUser);

        userRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getScheduleId(), schedule.getTitle(), schedule.getContents(), schedule.getStartDate(), schedule.getEndDate());
    }


    // todo 일정 전체 조회

    // todo 일정 세부 조회

    // todo 일정 수정

    // todo 일정 삭제
}
