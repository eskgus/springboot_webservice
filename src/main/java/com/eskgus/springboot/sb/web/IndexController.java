package com.eskgus.springboot.sb.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {  // 화면(페이지) URL 매핑

    @GetMapping("/")
    public String index() {
        // starter-mustache 써서 컨트롤러에서 문자열 반환할 때 경로(~/templates)와 확장자(.mustache) 자동으로 붙음
        // => src/main/resources/templates/index.mustache 리턴
        return "index";
    }

}
