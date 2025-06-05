package ru.blog_app.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.blog_app.blog.dto.request.CommentDtoRequest;
import ru.blog_app.blog.dto.response.CommentDtoResponse;
import ru.blog_app.blog.exception.CommentNotFoundException;
import ru.blog_app.blog.exception.PostNotFoundException;
import ru.blog_app.blog.exception.UserNotFoundException;
import ru.blog_app.blog.models.*;
import ru.blog_app.blog.repository.CommentRepository;
import ru.blog_app.blog.repository.PostRepository;
import ru.blog_app.blog.repository.UserRepository;
import ru.blog_app.blog.service.CommentService;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public CommentDtoResponse createComment(CommentDtoRequest commentDto, String currentUsername) {
        User author = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post not found"));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setAuthor(author);
        comment.setPost(post);

        Comment savedComment = commentRepository.save(comment);
        return convertToDto(savedComment);
    }

    @Override
    public CommentDtoResponse updateComment(Long id, CommentDtoRequest commentDto, String currentUsername) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        if (!comment.getAuthor().getUsername().equals(currentUsername)) {
            throw new SecurityException("You can only edit your own comments");
        }

        comment.setContent(commentDto.getContent());
        Comment updatedComment = commentRepository.save(comment);
        return convertToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long id, String currentUsername) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found"));

        if (!comment.getAuthor().getUsername().equals(currentUsername)) {
            throw new SecurityException("You can only delete your own comments");
        }

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentDtoResponse> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CommentDtoResponse convertToDto(Comment comment) {
        return CommentDtoResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .authorId(comment.getAuthor().getId())
                .authorUsername(comment.getAuthor().getUsername())
                .postId(comment.getPost().getId())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}