package com.eskgus.springboot.sb.web;

import com.eskgus.springboot.sb.config.auth.dto.SessionUser;
import com.eskgus.springboot.sb.service.posts.PostsService;
import com.eskgus.springboot.sb.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {  // 화면(페이지) URL 매핑

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {
        // addAttribute(String attributeName, Object attributeValue)
        // attributeValue(postsService.findAllDesc())를 attributeName("posts")으로 추가
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 httpSession.setAttribute("user", new SessionUser(user));로
        // 세션에 로그인된 사용자(SessionUser)의 attributes를 저장해놨음
        // 그 SessionUser의 attribute를 여기서 getAttribute() 써서 가져오는 거
        SessionUser user = (SessionUser)httpSession.getAttribute("user");

        if (user != null) {
            // 세션에 저장된 값이 있으면 model에 userName으로 추가
            // => 이거 가지고 index.mustache에서 {{#userName}}, {{^userName}}으로 userName 유무에 따라 로그인 버튼 만드는 거
            model.addAttribute("userName", user.getName());
        }

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
