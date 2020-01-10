package com.hachicore.springwebservicebook.service.posts;

import com.hachicore.springwebservicebook.domain.posts.Posts;
import com.hachicore.springwebservicebook.domain.posts.PostsRepository;
import com.hachicore.springwebservicebook.web.dto.PostsRequestDto;
import com.hachicore.springwebservicebook.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(Posts post) {
        return postsRepository.save(post).getId();
    }

    @Transactional
    public Long update(Long id, PostsRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new PostsResponseDto(post);
    }
}
