package com.eskgus.springboot.sb.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // (lombok) 필드의 Getter 메소드 자동 생성
@NoArgsConstructor  // (lombok) 기본 생성자 자동 추가 (public Posts() {}랑 같은 효과)
@Entity // jpa
public class Posts {    // 실제 db 테이블과 매칭될 클래스 (Entity 클래스)

    @Id // 테이블 PK
    // @GereratedValue: PK 생성 규칙
    // GenerationType.IDENTITY: auto increment (스프링 부트 2.0)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column: 굳이 선언 안 해도 해당 클래스의 필드는 모두 칼럼. 기본 값 외 변경 필요한 옵션이 있으면 사용
    // (문자열 기본 VARCHAR(255) -> title length: 500, content type: TEXT)
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 빌더 패턴 클래스 생성
    // (생성자(public Posts() {}) 위에 선언 시 생성자에 포함된 필드(title, content, author)만 빌더에 포함)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
