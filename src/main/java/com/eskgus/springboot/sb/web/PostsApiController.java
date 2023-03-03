package com.eskgus.springboot.sb.web;

import com.eskgus.springboot.sb.service.posts.PostsService;
import com.eskgus.springboot.sb.web.dto.PostsResponseDto;
import com.eskgus.springboot.sb.web.dto.PostsSaveRequestDto;
import com.eskgus.springboot.sb.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {   // Controller: API 요청받음

    // Bean 주입 방식: @Autowired, setter, 생성자
    // @RequiredArgsConstructor가 생성자 만들어줘서 @Autowired 안 씀 !
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
