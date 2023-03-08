package com.eskgus.springboot.sb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration  // @WebMvcTest가 WebMvcConfigurer(WebConfig.java)는 스캔하는데 일반 @Configuration은 스캔 x
@EnableJpaAuditing  // JPA Auditing 어노테이션 활성화
public class JpaConfig {
}
