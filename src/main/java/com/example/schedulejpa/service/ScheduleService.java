package com.example.schedulejpa.service;

import com.example.schedulejpa.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpa.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpa.entity.Schedule;
import com.example.schedulejpa.entity.User;
import com.example.schedulejpa.repository.ScheduleRepository;
import com.example.schedulejpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // todo 일정 등록
    public ScheduleResponseDto save(String title, String contents, LocalDateTime startTime, LocalDateTime endTime) {
        // todo 로그인 기능이랑 연동 필요
        User findUser = userRepository.findByUserOrElseThrow(name);
        Schedule schedule = new Schedule(title, contents, startTime, endTime);
        user.setUser(findUser);
        userRepository.save(schedule);
        return new ScheduleResponseDto(schedule.getScheduleId(),  schedule.getTitle(), schedule.getContents(), schedule.getStartDate(), schedule.getEndDate());
    }

    // todo 일정 전체 조회
    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleResponseDto::toDto)
                .toList();
    }

    // todo 일정 세부 조회
    public ScheduleResponseDto findById(Long scheduleId) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User writer = findSchedule.getUser();
        return new ScheduleResponseDto(
                writer.getName(),
                findSchedule.getScheduleId(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getStartDate(),
                findSchedule.getEndDate()
        );
    }

    // todo 일정 수정
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto dto) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        User writer = findSchedule.getUser();
        findSchedule.setTitle(dto.getTitle());
        findSchedule.setContents(dto.getContents());
        findSchedule.setStartDate(dto.getStartDate());
        findSchedule.setEndDate(dto.getEndDate());
        // 일정 저장
        scheduleRepository.save(findSchedule);
        return new ScheduleResponseDto(
                writer.getName(),
                findSchedule.getScheduleId(),
                findSchedule.getTitle(),
                findSchedule.getContents(),
                findSchedule.getStartDate(),
                findSchedule.getEndDate());
    }

    // todo 일정 삭제
    public void deleteById(Long scheduleId) {
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(scheduleId);
        scheduleRepository.delete(findSchedule);
    }
}
