package com.eskgus.springboot.sb.web;

import com.eskgus.springboot.sb.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)    // 테스트 진행 시 JUnit에 내장된 실행자 말고 해당 클래스의 실행자(SpringRunner) 실행시킴
// @WebMvcTest: Can be used when a test focuses only on Spring MVC components
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        // SecurityConfig를 생성하려면 CustomOAuth2UserService가 필요
        // -> @WebMvcTest는 @Repository, @Service, @Component를 스캔하지 x
        // -> SecurityConfig는 스캔하지 않게 필터링 (excludeFilters)
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {

    // Marks a constructor, field, setter method, or config method as to be autowired
    // by Spring's dependency injection facilities.
    @Autowired
    private MockMvc mvc;   // Main entry point for server-side Spring MVC test support.

    @Test
    @WithMockUser(roles="USER")
    public void return_hello() throws Exception {
        String hello = "hello";

        // andExpect(): 검증
        // isOk(): HTTP Header의 status가 200인가
        // content(): 응답 본문의 내용
        //  string(String expectedContent): 본문의 내용이 expectedContent인가
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }

    @Test
    @WithMockUser(roles="USER")
    public void return_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        // param(String): API 테스트할 때 사용될 요청 파라미터 설정
        // jasonPath("$.필드명"): json 응답 값을 필드별로 검증
        mvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
