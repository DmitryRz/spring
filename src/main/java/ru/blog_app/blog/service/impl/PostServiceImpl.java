package ru.blog_app.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.blog_app.blog.dto.PostDtoResponse;
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
    public PostDtoResponse createPost(PostDtoResponse postDtoResponse) {
        User user = userRepository.findById(postDtoResponse.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = Post.builder()
                .title(postDtoResponse.getTitle())
                .content(postDtoResponse.getContent())
                .user(user)
                .createTime(LocalDateTime.now())
                .build();

        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));
        postRepository.delete(post);
    }

    @Override
    public PostDtoResponse updatePost(PostDtoResponse postDtoResponse, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        post.setTitle(postDtoResponse.getTitle());
        post.setContent(postDtoResponse.getContent());
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
                .createTime(post.getCreateTime())
                .build();
    }
}