package com.example.schedulejpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    // 유저 ID	user_id	Not Null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // 이메일	email	Not Null
    @Column(nullable = false, unique = true)
    private String email;

    // 유저명	name	Not Null
    @Column(nullable = false)
    private String name;

    // 비밀번호	password	Not Null
    @Column(nullable = false)
    private String password;

    // 유저 생성자
    // 유저 ID는 제외
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
