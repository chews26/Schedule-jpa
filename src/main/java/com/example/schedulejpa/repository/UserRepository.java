package com.example.schedulejpa.repository;

import com.example.schedulejpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 로그인 기능
    // email로 값 유무 찾기
    Optional<User> findByEmail(String email);

    User findByUserId(Long userId);
}
