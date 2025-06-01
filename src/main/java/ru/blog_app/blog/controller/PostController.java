package ru.blog_app.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.blog_app.blog.dto.request.CreatePostRequest;
import ru.blog_app.blog.dto.response.PostDtoResponse;
import ru.blog_app.blog.service.PostService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<PostDtoResponse> getPostById(@PathVariable Long id) {
        log.info("Вызов метода getPostById с параметром {}", id);
        PostDtoResponse response = postService.getPost(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDtoResponse>> getAllPosts() {
        log.info("Вызов метода getAllPosts");
        List<PostDtoResponse> response = postService.getAllPosts();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDtoResponse> createPost(@RequestBody CreatePostRequest request, Authentication authentication) {
        log.info("Вызов метода createPost с параметром {}", request);
        PostDtoResponse response = postService.createPost(request, authentication.getName());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/put")
    public ResponseEntity<PostDtoResponse> updatePost(@RequestBody CreatePostRequest request, @PathVariable Long id, Authentication authentication) {
        log.info("Вызов метода updatePost с параметрами {} и {}", request, id);
        PostDtoResponse response = postService.updatePost(request, id, authentication.getName());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id, Authentication authentication) {
        log.info("Вызов метода deletePost с параметром {}", id);
        postService.deletePost(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
