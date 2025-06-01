package ru.blog_app.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.blog_app.blog.dto.request.CreatePostRequest;
import ru.blog_app.blog.dto.response.PostDtoResponse;
import ru.blog_app.blog.exception.PostNotFoundException;
import ru.blog_app.blog.models.Post;
import ru.blog_app.blog.models.User;
import ru.blog_app.blog.repository.PostRepository;
import ru.blog_app.blog.repository.UserRepository;
import ru.blog_app.blog.service.PostService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    
    @Override
    public PostDtoResponse createPost(CreatePostRequest request, String currentUsername) {
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .createdAt(LocalDateTime.now())
                .build();

        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    @Override
    public void deletePost(Long id, String currentUsername) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        if (!post.getUser().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("You can't delete this post");
        }

        postRepository.delete(post);
    }

    @Override
    public PostDtoResponse updatePost(CreatePostRequest request, Long id, String currentUsername) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        if (!post.getUser().getUsername().equals(currentUsername)) {
            throw new AccessDeniedException("You can't update this post");
        }

        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        Post updatedPost = postRepository.save(post);

        return convertToDto(updatedPost);
    }

    @Override
    public PostDtoResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
        return convertToDto(post);
    }

    @Override
    public List<PostDtoResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private PostDtoResponse convertToDto(Post post) {
        return PostDtoResponse.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .userId(post.getUser().getId())
                .createTime(post.getCreatedAt())
                .build();
    }
}