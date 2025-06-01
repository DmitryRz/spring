package ru.blog_app.blog.service;

import ru.blog_app.blog.dto.request.CommentDtoRequest;
import ru.blog_app.blog.dto.response.CommentDtoResponse;
import java.util.List;

/**
 * Интерфейс сервиса комментариев.
 */
public interface CommentService {

    /**
     * Создает новый комментарий.
     *
     * @param commentDto объект DTO с данными комментария
     * @param currentUsername имя пользователя, создавшего комментарий
     * @return объект DTO с данными созданного комментария
     */
    CommentDtoResponse createComment(CommentDtoRequest commentDto, String currentUsername);

    /**
     * Обновляет существующий комментарий.
     *
     * @param id идентификатор комментария
     * @param commentDto объект DTO с новыми данными комментария
     * @param currentUsername имя пользователя, обновляющего комментарий
     * @return объект DTO с данными обновленного комментария
     */
    CommentDtoResponse updateComment(Long id, CommentDtoRequest commentDto, String currentUsername);

    /**
     * Удаляет комментарий.
     *
     * @param id идентификатор комментария
     * @param currentUsername имя пользователя, удаляющего комментарий
     */
    void deleteComment(Long id, String currentUsername);

    /**
     * Возвращает список комментариев для заданного поста.
     *
     * @param postId идентификатор поста
     * @return список объектов DTO с данными комментариев
     */
    List<CommentDtoResponse> getCommentsByPostId(Long postId);
}
