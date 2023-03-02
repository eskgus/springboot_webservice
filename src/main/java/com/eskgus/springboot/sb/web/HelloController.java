package com.eskgus.springboot.sb.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 json을 반환하는 컨트롤러로 만들어 줌
public class HelloController {

    // mapping HTTP GET requests onto specific handler methods (@RequestMapping(method = {RequestMethod.GET}))
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
