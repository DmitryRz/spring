package ru.blog_app.blog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.blog_app.blog.enums.Role;

import java.time.LocalDateTime;

import java.util.List;

@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createdAt = LocalDateTime.now();
}
