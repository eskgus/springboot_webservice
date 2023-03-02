package com.eskgus.springboot.sb.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 json을 반환하는 컨트롤러로 만들어 줌
public class HelloController {

    // @GetMapping("/hello"): mapping HTTP GET requests onto specific handler methods
    // (@RequestMapping(path = "/path", method = {RequestMethod.GET})랑 같은 거)
    @RequestMapping(method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}
