package com.example.schedulejpa.dto.schedule;

import com.example.schedulejpa.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private final String name;

    private final Long scheduleId;

    private final String title;

    private final String contents;

    private final LocalDateTime startDate;

    private final LocalDateTime endDate;

    public ScheduleResponseDto(String name, Long scheduleId, String title, String contents, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.scheduleId = scheduleId;
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public static ScheduleResponseDto toDto(Schedule schedule) {
        return new ScheduleResponseDto(
                schedule.getUser().getName(),
                schedule.getScheduleId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getStartDate(),
                schedule.getEndDate()
        );
    }
}
