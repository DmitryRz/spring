package ru.blog_app.blog.service;

import ru.blog_app.blog.dto.request.AuthRequest;
import ru.blog_app.blog.dto.response.UserDtoResponse;

/**
 * Интерфейс сервиса для работы с пользователями.
 */
public interface UserService {

    /**
     * Получает информацию о пользователе по его идентификатору.
     *
     * @param id идентификатор пользователя
     * @return информация о пользователе в формате UserDtoResponse
     */
    UserDtoResponse getUser(Long id);

    /**
     * Удаляет пользователя по его идентификатору и имени текущего пользователя.
     *
     * @param id идентификатор пользователя
     * @param currentUsername имя текущего пользователя
     */
    void deleteUser(Long id, String currentUsername);
}
