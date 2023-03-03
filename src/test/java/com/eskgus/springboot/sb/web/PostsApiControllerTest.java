package com.eskgus.springboot.sb.web;

import com.eskgus.springboot.sb.domain.posts.Posts;
import com.eskgus.springboot.sb.domain.posts.PostsRepository;
import com.eskgus.springboot.sb.web.dto.PostsSaveRequestDto;
import com.eskgus.springboot.sb.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
// @WebMvcTest는 JPA 기능 작동 x -> JPA 기능까지 한꺼번에 테스트할 때는 @SpringBootTest, TestRestTemplate 사용
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;  // http 통신에 사용하는 템플릿

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void upload_posts() throws Exception {

        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title).content(content).author("author").build();

        String url = "http://localhost:" + port + "/api/v1/posts";  // port: RANDOM_PORT

        // when
        // postForEntity(String url, Object request, Class<T> responseType)
        // : Create a new resource by POSTing the given object to the URI template,
        // and returns the response as ResponseEntity
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        // getStatusCode(): Return the HTTP status code of the response
        // HttpStatus.OK: Enumeration of HTTP status codes (200 OK)
        // isGreaterThan(): Verifies that the actual value(responseEntity.getBody()) is greater than the given one(0L)
        // 0L: Long 0
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        // get(int index): Returns the element at the specified position in this list
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }

    @Test
    public void update_posts() throws Exception {

        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title").content("content").author("author").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle).content(expectedContent).build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }

}
