package com.example.schedulejpa.dto.schedule;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    @NotEmpty(message = "일정 제목은 필수 입력 항목입니다.")
    private final String title;

    @NotEmpty(message = "일정 내용은 필수 입력 항목입니다.")
    private final String contents;

    @NotEmpty(message = "시작일은 필수 입력 항목입니다.")
    private final LocalDateTime startDate;

    @NotEmpty(message = "종료일은 필수 입력 항목입니다.")
    private final LocalDateTime endDate;

    public ScheduleRequestDto(String title, String contents, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
