package com.example.schedulejpa.service;

import com.example.schedulejpa.common.Const;
import com.example.schedulejpa.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpa.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpa.dto.user.login.LoginResponseDto;
import com.example.schedulejpa.entity.Schedule;
import com.example.schedulejpa.entity.User;
import com.example.schedulejpa.repository.ScheduleRepository;
import com.example.schedulejpa.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;
    private final HttpSession session;

    // todo 일정 등록
    public ScheduleResponseDto save(
            ScheduleRequestDto dto
    ) {
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }

        // 로그인된 사용자의 User 엔티티 조회
        User findUser = userRepository.findById(loginUser.getUserId())
                .orElseThrow(() -> new RuntimeException("유효하지 않은 사용자입니다."));

        Schedule schedule = new Schedule(
                dto.getTitle(),
                dto.getContents(),
                dto.getStartDate(),
                dto.getEndDate()
        );
        schedule.setUser(findUser); // 작성자 설정
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponseDto.toDto(savedSchedule);
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
