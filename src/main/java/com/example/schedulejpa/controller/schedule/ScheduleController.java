package com.example.schedulejpa.controller.schedule;

import com.example.schedulejpa.common.Const;
import com.example.schedulejpa.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpa.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpa.dto.user.login.LoginResponseDto;
import com.example.schedulejpa.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // todo 일정 등록
    // todo login기능이랑 연동해서 입력자 반환 필요
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@RequestBody ScheduleRequestDto requestdto, HttpSession session) {

        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 로그인 상태가 아니면 401 반환
        }

        ScheduleResponseDto scheduleResponseDto = scheduleService.save(
                requestdto.getTitle(),
                requestdto.getContents(),
                requestdto.getStartDate(),
                requestdto.getEndDate()
        );
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // todo 일정 전체 조회
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAll(HttpSession session) {
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 로그인 상태가 아니면 401 반환
        }

        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    // todo 일정 세부 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id, HttpSession session) {
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 로그인 상태가 아니면 401 반환
        }

        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // todo 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto, HttpSession session) {
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 로그인 상태가 아니면 401 반환
        }

        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto), HttpStatus.OK);
    }

    // todo 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto, HttpSession session) {
        LoginResponseDto loginUser = (LoginResponseDto) session.getAttribute(Const.LOGIN_USER);
        if (loginUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // 로그인 상태가 아니면 401 반환
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
