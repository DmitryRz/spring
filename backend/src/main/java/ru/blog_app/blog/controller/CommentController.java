package ru.blog_app.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.blog_app.blog.dto.request.CommentDtoRequest;
import ru.blog_app.blog.dto.response.CommentDtoResponse;
import ru.blog_app.blog.models.User;
import ru.blog_app.blog.service.CommentService;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/comments")
@CrossOrigin(origins = "http://localhost:3000")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDtoResponse> createComment(
            @PathVariable Long postId,
            @RequestBody CommentDtoRequest commentDto,
            Authentication authentication) {
        commentDto.setPostId(postId);
        CommentDtoResponse response = commentService.createComment(commentDto, authentication.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDtoResponse>> getCommentsByPostId(@PathVariable Long postId) {
        List<CommentDtoResponse> response = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDtoResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentDtoRequest commentDto,
            Authentication authentication) {
        CommentDtoResponse response = commentService.updateComment(commentId, commentDto, authentication.getName());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            Authentication authentication) {
        commentService.deleteComment(commentId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}