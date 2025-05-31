package ru.blog_app.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blog_app.blog.dto.PostDtoResponse;
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

    @PostMapping("/create")
    public ResponseEntity<PostDtoResponse> createPost(@RequestBody PostDtoResponse postDtoResponse) {
        log.info("Вызов метода createPost с параметром {}", postDtoResponse);
        PostDtoResponse response = postService.createPost(postDtoResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<PostDtoResponse> updatePost(@RequestBody PostDtoResponse postDtoResponse, @PathVariable Long id) {
        log.info("Вызов метода updatePost с параметрами {} и {}", postDtoResponse, id);
        PostDtoResponse response = postService.updatePost(postDtoResponse, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        log.info("Вызов метода deletePost с параметром {}", id);
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDtoResponse>> getAllPosts() {
        log.info("Вызов метода getAllPosts");
        List<PostDtoResponse> response = postService.getAllPosts();
        return ResponseEntity.ok(response);
    }
}
