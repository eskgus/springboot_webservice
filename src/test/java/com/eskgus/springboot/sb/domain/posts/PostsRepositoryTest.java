package com.eskgus.springboot.sb.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After  // 단위 테스트 끝날 때마다 수행되는 메서드 지정 (테스트 간 데이터 침범 막기 위해 사용)
    public void cleanup() {
        postsRepository.deleteAll();    // => 단위 테스트 끝날 때마다 posts에 써진 거 다 지우기
    }

    @Test
    public void load_posts() {

        // given
        String title = "테스크 게시글";
        String content = "테스트 본문";

        // postsRepository.save(): 테이블 posts에 insert/update 쿼리 실행
        postsRepository.save(Posts.builder().title(title).content(content).author("eskgus012@naver.com").build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

}
