package com.example.schedulejpa.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleRequestDto {

    @NotBlank(message = "일정 제목은 필수 입력 항목입니다.")
    private final String title;

    @NotBlank(message = "일정 내용은 필수 입력 항목입니다.")
    private final String contents;

    @NotNull(message = "시작일은 필수 입력 항목입니다.")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime startDate;

    @NotNull(message = "종료일은 필수 입력 항목입니다.")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime endDate;

    public ScheduleRequestDto(String title, String contents, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
