package com.blog.blogrestapi.payload;

import com.blog.blogrestapi.entity.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;
}

// DTO -  data transfer object -> a design pattern used to pass the data with
// multiple params from client to server in one shot (to avoid remote calls or server calls)
// the main advantage of using it is that the implementation details of JPA entities can be hidden