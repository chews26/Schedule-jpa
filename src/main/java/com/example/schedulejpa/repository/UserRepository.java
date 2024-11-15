package com.example.schedulejpa.repository;

import com.example.schedulejpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // findByEmail 메서드 추가
    Optional<User> findByEmail(String email);

    // DB email(로그인 시 사용되는 id) 존재여부 확인
    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 이메일입니다." + email));
    }
}