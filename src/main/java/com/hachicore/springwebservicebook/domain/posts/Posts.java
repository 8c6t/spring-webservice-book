package com.hachicore.springwebservicebook.domain.posts;

import com.hachicore.springwebservicebook.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter @Setter(value = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
