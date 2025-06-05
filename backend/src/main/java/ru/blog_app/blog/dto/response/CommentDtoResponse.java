package ru.blog_app.blog.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentDtoResponse {
    private Long id;
    private String content;
    private Long authorId;
    private String authorUsername;
    private Long postId;
    private LocalDateTime createdAt;
}