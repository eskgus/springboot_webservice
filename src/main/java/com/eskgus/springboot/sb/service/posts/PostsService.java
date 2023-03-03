package com.eskgus.springboot.sb.service.posts;

import com.eskgus.springboot.sb.domain.posts.PostsRepository;
import com.eskgus.springboot.sb.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService { // Service: 트랜잭션, 도메인 기능 간 순서 보장함

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

}
