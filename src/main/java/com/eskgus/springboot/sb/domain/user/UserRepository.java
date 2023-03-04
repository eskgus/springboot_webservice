package com.eskgus.springboot.sb.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 소셜 로그인으로 반환되는 값 중 email을 통해 이미 있는 사용자인지 아닌지 판단하기 위한 메서드
    Optional<User> findByEmail(String email);

}
