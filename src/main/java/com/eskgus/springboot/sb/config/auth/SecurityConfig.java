package com.eskgus.springboot.sb.config.auth;

import com.eskgus.springboot.sb.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity  // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http    // h2-console 화면 사용하기 위해서 해당 옵션들 disable()
                .csrf().disable().headers().frameOptions().disable()
                // URL 별 권한 관리 설정하는 옵션의 시작점 (authorizeRequests() 선언해야 antMatchers() 사용 가능)
                .and().authorizeRequests()
                // antMatchers(URL): 권한 관리 대상 지정하는 옵션
                // permitAll(): 전체 열람 권한
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll()
                // hasRole(Role.USER.name()): USER 권한 가진 사람만
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // 나머지 URL들은 인증된(로그인한) 사용자들에게만 허용
                .anyRequest().authenticated()
                // 로그아웃 성공하면 / 주소로 이동
                .and().logout().logoutSuccessUrl("/")
                // oauth2Login(): OAuth 2 로그인 기능에 대한 설정
                // userInfoEndpoint(): OAuth 2 로그인 성공하고 사용자 정보 가져올 때 설정들 담당
                // userService(): 소셜 로그인 성공 시 후속 조치 진행할 UserService 인터페이스 구현체 등록
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);

    }

}
