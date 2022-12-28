package com.blog.blogrestapi.controller;

import com.blog.blogrestapi.payload.CommentDto;
import com.blog.blogrestapi.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @Valid @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentById(@PathVariable long postId, @PathVariable long commentId) {
        return commentService.getCommentById(postId, commentId);
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public CommentDto updateComment(@PathVariable long postId,
                                    @PathVariable long commentId,
                                    @Valid @RequestBody CommentDto commentDto) {
        return commentService.updateComment(postId, commentId, commentDto);
    }

    @DeleteMapping("posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.ok("Comment deleted successfully!");
    }
}
