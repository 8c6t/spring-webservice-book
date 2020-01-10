package com.hachicore.springwebservicebook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class PostsRequestDto {

    private String title;
    private String content;
    private String author;

}
