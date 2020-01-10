package com.hachicore.springwebservicebook.web.dto;

import com.hachicore.springwebservicebook.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getTitle();
        this.author = post.getAuthor();
    }

}
