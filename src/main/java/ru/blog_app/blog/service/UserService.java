package ru.blog_app.blog.service;

import ru.blog_app.blog.dto.RegisterUserRequest;
import ru.blog_app.blog.dto.UserDtoResponse;
import ru.blog_app.blog.models.User;

public interface UserService {

    UserDtoResponse getUser(Long id);
    UserDtoResponse createUser(RegisterUserRequest request);
    UserDtoResponse putUser(UserDtoResponse request, Long id);
    void deleteUser(Long id);
}
