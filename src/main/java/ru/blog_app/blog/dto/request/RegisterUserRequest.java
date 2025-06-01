package ru.blog_app.blog.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.blog_app.blog.dto.response.UserDtoResponse;

@EqualsAndHashCode(callSuper = true)
@Data
public class RegisterUserRequest extends UserDtoResponse {
    private String password;
}
