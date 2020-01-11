package com.hachicore.springwebservicebook.web.dto;

import com.hachicore.springwebservicebook.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.modifiedDate = post.getModifiedDate();
    }

}
