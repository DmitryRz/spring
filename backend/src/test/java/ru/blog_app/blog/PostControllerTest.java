package ru.blog_app.blog;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import ru.blog_app.blog.controller.PostController;
import ru.blog_app.blog.dto.request.CreatePostRequest;
import ru.blog_app.blog.dto.response.PostDtoResponse;
import ru.blog_app.blog.service.PostService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        authentication = mock(Authentication.class);
        lenient().when(authentication.getName()).thenReturn("testUser");
    }

    @Test
    public void testGetPostById() {
        Long id = 1L;
        PostDtoResponse response = PostDtoResponse.builder().build();
        when(postService.getPost(id)).thenReturn(response);

        ResponseEntity<PostDtoResponse> result = postController.getPostById(id);

        assertEquals(response, result.getBody());
        verify(postService, times(1)).getPost(id);
    }

    @Test
    public void testGetAllPosts() {
        List<PostDtoResponse> response = Collections.emptyList();
        when(postService.getAllPosts()).thenReturn(response);

        ResponseEntity<List<PostDtoResponse>> result = postController.getAllPosts();

        assertEquals(response, result.getBody());
        verify(postService, times(1)).getAllPosts();
    }

    @Test
    public void testCreatePost() {
        CreatePostRequest request = new CreatePostRequest();
        PostDtoResponse response = PostDtoResponse.builder().build();
        when(postService.createPost(request, "testUser")).thenReturn(response);

        ResponseEntity<PostDtoResponse> result = postController.createPost(request, authentication);

        assertEquals(response, result.getBody());
        verify(postService, times(1)).createPost(request, "testUser");
    }

    @Test
    public void testUpdatePost() {
        Long id = 1L;
        CreatePostRequest request = new CreatePostRequest();
        PostDtoResponse response = PostDtoResponse.builder().build();
        when(postService.updatePost(request, id, "testUser")).thenReturn(response);

        ResponseEntity<PostDtoResponse> result = postController.updatePost(request, id, authentication);

        assertEquals(response, result.getBody());
        verify(postService, times(1)).updatePost(request, id, "testUser");
    }

    @Test
    public void testDeletePost() {
        Long id = 1L;

        postController.deletePost(id, authentication);

        verify(postService, times(1)).deletePost(id, "testUser");
    }
}
