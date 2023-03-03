package com.eskgus.springboot.sb.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// DB 접근자 (Dao)
// JpaRepository<Entity 클래스, PK 타입> 상속 -> 기본 CRUD 메서드 자동 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
