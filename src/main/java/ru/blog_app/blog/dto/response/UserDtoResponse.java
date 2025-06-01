package ru.blog_app.blog.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDtoResponse {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Email
    private String email;
    private LocalDateTime createdAt;
}
