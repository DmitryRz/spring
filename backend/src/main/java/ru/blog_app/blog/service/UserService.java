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
     * @return информация о пользователе
     */
    UserDtoResponse getUser(Long id);

    /**
     * Создает нового пользователя на основе предоставленных данных.
     *
     * @param request данные для создания пользователя
     * @return информация о созданном пользователе
     */
    UserDtoResponse createUser(AuthRequest request);

    /**
     * Обновляет информацию о пользователе на основе предоставленных данных.
     *
     * @param request данные для обновления пользователя
     * @param id идентификатор пользователя
     * @return информация об обновленном пользователе
     */
    UserDtoResponse putUser(AuthRequest request, Long id, String currentUsername);

    /**
     * Удаляет пользователя по его идентификатору.
     *
     * @param id идентификатор пользователя
     */
    void deleteUser(Long id, String currentUsername);
}
