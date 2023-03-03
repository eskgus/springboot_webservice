package com.eskgus.springboot.sb.service.posts;

import com.eskgus.springboot.sb.domain.posts.Posts;
import com.eskgus.springboot.sb.domain.posts.PostsRepository;
import com.eskgus.springboot.sb.web.dto.PostsResponseDto;
import com.eskgus.springboot.sb.web.dto.PostsSaveRequestDto;
import com.eskgus.springboot.sb.web.dto.PostsUpdateRequestDto;
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

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        // findById():  org.springframework.data.repository.CrudRepository<T, ID>
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                // IllegalArgumentException: if id is null
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

}
