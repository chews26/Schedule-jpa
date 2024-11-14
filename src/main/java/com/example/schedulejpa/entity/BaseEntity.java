package com.example.schedulejpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    // 작성일	creation_date	Not Null
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    // 수정일	modified_date	Not Null
    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
