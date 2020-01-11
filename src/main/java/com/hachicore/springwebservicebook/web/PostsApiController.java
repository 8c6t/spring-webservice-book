package com.hachicore.springwebservicebook.web;

import com.hachicore.springwebservicebook.domain.posts.Posts;
import com.hachicore.springwebservicebook.service.posts.PostsService;
import com.hachicore.springwebservicebook.web.dto.PostsRequestDto;
import com.hachicore.springwebservicebook.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final ModelMapper modelMapper;
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsRequestDto requestDto) {
        Posts post = modelMapper.map(requestDto, Posts.class);
        return postsService.save(post);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
