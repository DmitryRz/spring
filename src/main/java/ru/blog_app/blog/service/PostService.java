package ru.blog_app.blog.service;

import ru.blog_app.blog.dto.PostDtoResponse;
import ru.blog_app.blog.models.Post;

import java.util.List;

public interface PostService {
    PostDtoResponse createPost(PostDtoResponse postDtoResponse);
    void deletePost(Long id);
    PostDtoResponse updatePost(PostDtoResponse postDtoResponse, Long id);
    PostDtoResponse getPost(Long id);
    List<PostDtoResponse> getAllPosts();
}
