package com.example.schedulejpa.controller.schedule;

import com.example.schedulejpa.dto.schedule.ScheduleRequestDto;
import com.example.schedulejpa.dto.schedule.ScheduleResponseDto;
import com.example.schedulejpa.service.ScheduleService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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

    // 일정 등록
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> save(@Valid @RequestBody ScheduleRequestDto requestDto, HttpSession session) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.save(requestDto);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
    }

    // 일정 전체 조회
    @GetMapping("/all")
    public ResponseEntity<List<ScheduleResponseDto>> findAll(HttpSession session) {
        List<ScheduleResponseDto> scheduleResponseDtoList = scheduleService.findAll();
        return new ResponseEntity<>(scheduleResponseDtoList, HttpStatus.OK);
    }

    // 일정 세부 조회
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(@PathVariable Long id, HttpSession session) {
        ScheduleResponseDto scheduleResponseDto = scheduleService.findById(id);
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id,@Valid @RequestBody ScheduleRequestDto dto, HttpSession session) {
        return new ResponseEntity<>(scheduleService.updateSchedule(id, dto), HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, HttpSession session) {
        scheduleService.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}