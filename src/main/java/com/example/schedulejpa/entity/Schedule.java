package com.example.schedulejpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "schedule")
public class Schedule extends BaseEntity {

    // 일정 ID   schedule_id	Not Null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    //	할일 제목	title	Not Null
    @Column(nullable = false)
    private String title;

    //	할일 내용	contents	Not Null
    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    //	시작일	start_date	Not Null
    @Column(nullable = false)
    private LocalDateTime startDate;

    //	종료일	end_date	Not Null
    @Column(nullable = false)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Schedule() {
    }

    public Schedule(String title, String contents, LocalDateTime startDate, LocalDateTime endDate) {
        this.title = title;
        this.contents = contents;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
