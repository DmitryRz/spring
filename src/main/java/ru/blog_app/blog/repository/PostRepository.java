package ru.blog_app.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blog_app.blog.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
