package ru.blog_app.blog.service;

import ru.blog_app.blog.dto.request.CreatePostRequest;
import ru.blog_app.blog.dto.response.PostDtoResponse;

import java.util.List;

/**
 * Интерфейс сервиса для работы с постами.
 */
public interface PostService {

    /**
     * Возвращает пост по его идентификатору.
     *
     * @param id идентификатор поста
     * @return объект DTO с данными поста
     */
    PostDtoResponse getPost(Long id);

    /**
     * Возвращает список всех постов.
     *
     * @return список объектов DTO с данными постов
     */
    List<PostDtoResponse> getAllPosts();

    /**
     * Создает новый пост.
     *
     * @param request объект DTO с данными поста
     * @param currentUsername имя текущего пользователя
     * @return объект DTO с данными созданного поста
     */
    PostDtoResponse createPost(CreatePostRequest request, String currentUsername);

    /**
     * Удаляет пост по его идентификатору.
     *
     * @param id идентификатор поста
     * @param currentUsername имя текущего пользователя
     */
    void deletePost(Long id, String currentUsername);

    /**
     * Обновляет пост по его идентификатору.
     *
     * @param request объект DTO с данными поста
     * @param id идентификатор поста
     * @param currentUsername имя текущего пользователя
     * @return объект DTO с данными обновленного поста
     */
    PostDtoResponse updatePost(CreatePostRequest request, Long id, String currentUsername);
}
