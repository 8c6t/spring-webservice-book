package com.hachicore.springwebservicebook.web;

import com.hachicore.springwebservicebook.domain.posts.Posts;
import com.hachicore.springwebservicebook.domain.posts.PostsRepository;
import com.hachicore.springwebservicebook.web.dto.PostsRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class PostsApiControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("Post 등록")
    void postSave() throws Exception {
        // given
        String title = "title";
        String content = "content";

        PostsRequestDto requestDto = PostsRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        ResponseEntity<Long> response = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(response.getBody()).isGreaterThan(0L);

    }

    @Test
    @DisplayName("Post 수정")
    void postUpdate() throws Exception {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsRequestDto requestDto = PostsRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> response = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertThat(response.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertEquals(all.get(0).getTitle(), expectedTitle);
        assertEquals(all.get(0).getContent(), expectedContent);
    }

    @Test
    @DisplayName("BaseTimeEntity 등록")
    void baseTimeEntityTest() {
        // given
        LocalDateTime now = LocalDateTime.of(2020, 1, 10, 0, 0, 0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build()
        );

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts post = postsList.get(0);

        log.info(">>>>>>>> createdDate=" + post.getCreatedDate() + ", modifiedDate=" + post.getModifiedDate());

        assertThat(post.getCreatedDate()).isAfter(now);
        assertThat(post.getModifiedDate()).isAfter(now);
    }

}