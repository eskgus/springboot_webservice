package com.eskgus.springboot.sb.web;

import com.eskgus.springboot.sb.service.posts.PostsService;
import com.eskgus.springboot.sb.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {  // 화면(페이지) URL 매핑

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        // addAttribute(String attributeName, Object attributeValue)
        // attributeValue를 attributeName으로 추가
        model.addAttribute("posts", postsService.findAllDesc());

        // starter-mustache 써서 컨트롤러에서 문자열 반환할 때 경로(~/templates)와 확장자(.mustache) 자동으로 붙음
        // => src/main/resources/templates/index.mustache 리턴
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
