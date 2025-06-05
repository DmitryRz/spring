package ru.blog_app.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blog_app.blog.models.Comment;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPostId(Long postId);
    List<Comment> findByAuthorId(Long userId);
}