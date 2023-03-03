package com.eskgus.springboot.sb.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드(createdDate, modifiedDate)도 칼럼으로 인식
// @EntityListeners: Specifies the callback listener classes to be used for an entity or mapped superclass
// AuditingEntityListener: JPA entity listener to capture auditing information on persiting and updating entities
@EntityListeners(AuditingEntityListener.class)  // BaseTimeEntity 클래스에 Auditing 기능 포함시킴
public abstract class BaseTimeEntity {

    @CreatedDate    // Entity 생성되어 저장될 때 시간 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate   // Entity 값 변경할 때 시간 자동 저장
    private LocalDateTime modifiedDate;

}
