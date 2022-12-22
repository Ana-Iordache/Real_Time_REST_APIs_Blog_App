package com.blog.blogrestapi.repository;

import com.blog.blogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

// we don't need to pass @Repository because
// JpaRepository has an implementation class called SimpleJpaRepository
// that is annotated with @Repository
// same with @Transactional
public interface PostRepository extends JpaRepository<Post, Long> {
}
