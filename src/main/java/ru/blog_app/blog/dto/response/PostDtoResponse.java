package ru.blog_app.blog.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class PostDtoResponse {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime createTime;
}