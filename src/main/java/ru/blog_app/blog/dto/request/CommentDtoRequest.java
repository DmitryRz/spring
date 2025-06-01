package ru.blog_app.blog.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDtoRequest {
    @NotBlank
    @Size(min = 3, max = 1000)
    private String content;
    private Long postId;
}