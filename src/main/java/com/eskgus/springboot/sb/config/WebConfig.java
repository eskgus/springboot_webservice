package com.eskgus.springboot.sb.config;

import com.eskgus.springboot.sb.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
// Indicates that a class declares one or more @Bean methods and may be processed
// by the Spring container to generate bean definitions and service requests for those beans at runtime
@Configuration
// LoginUserArgumentResolver를 스프링이 인식할 수 있게 WebMvcConfigurer 인터페이스에 추가하는 클래스
public class WebConfig implements WebMvcConfigurer {

    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    // HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야 함 !
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }

}
