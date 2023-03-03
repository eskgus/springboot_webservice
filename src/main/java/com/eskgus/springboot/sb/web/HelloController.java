package com.eskgus.springboot.sb.web;

import com.eskgus.springboot.sb.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 json을 반환하는 컨트롤러로 만들어 줌
public class HelloController {

    // @GetMapping("/hello"): mapping HTTP GET requests onto specific handler methods
    // (@RequestMapping(path = "/path", method = {RequestMethod.GET})랑 같은 거)
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    // @RequestParam(): 외부에서 API(HelloControlloer)로 넘긴 파라미터 가져오기
    // -> 외부에서 @RequestParam("name")이라는 이름으로 넘긴 파라미터를 메소드 파라미터 String name에 저장
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}