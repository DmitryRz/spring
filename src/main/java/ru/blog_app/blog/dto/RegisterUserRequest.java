package ru.blog_app.blog.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterUserRequest extends UserDtoResponse {
    private String password;
}
