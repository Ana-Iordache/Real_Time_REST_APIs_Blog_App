package com.blog.blogrestapi.repository;

import com.blog.blogrestapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // custom query
    List<Comment> findByPostId(long postId);
}
