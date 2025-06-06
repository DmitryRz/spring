package ru.blog_app.blog.service;

import ru.blog_app.blog.dto.request.AuthRequest;

/**
 * Интерфейс сервиса для работы с аутентификацией.
 */
public interface AuthService {

    /**
     * Регистрирует нового пользователя.
     *
     * @param request объект запроса на регистрацию
     */
    void register(AuthRequest request);

    /**
     * Выполняет вход пользователя в систему.
     *
     * @param request объект запроса на вход
     * @return токен аутентификации
     */
    String login(AuthRequest request);
}

