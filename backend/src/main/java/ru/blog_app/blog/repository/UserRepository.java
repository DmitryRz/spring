package ru.blog_app.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.blog_app.blog.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
}
