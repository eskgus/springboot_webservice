package com.eskgus.springboot.sb.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)    // 테스트 진행 시 JUnit에 내장된 실행자 말고 해당 클래스의 실행자(SpringRunner) 실행시킴
@WebMvcTest(controllers = HelloController.class)    // Can be used when a test focuses only on Spring MVC components
public class HelloControllerTest {

    // Marks a constructor, field, setter method, or config method as to be autowired
    // by Spring's dependency injection facilities.
    @Autowired
    private MockMvc mvc;   // Main entry point for server-side Spring MVC test support.

    @Test
    public void return_hello() throws Exception {
        String hello = "hello";

        // andExpect(): 검증
        // isOk(): HTTP Header의 status가 200인가
        // content(): 응답 본문의 내용
        //  string(String expectedContent): 본문의 내용이 expectedContent인가
        mvc.perform(get("hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }
}