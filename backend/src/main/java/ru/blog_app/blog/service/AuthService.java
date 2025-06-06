package ru.blog_app.blog.service;

import ru.blog_app.blog.dto.request.AuthRequest;

public interface AuthService {
    void register(AuthRequest request);
    String login(AuthRequest request);
}
