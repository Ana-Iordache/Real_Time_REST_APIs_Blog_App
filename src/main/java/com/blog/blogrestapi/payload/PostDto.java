package com.blog.blogrestapi.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private long id;

    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
}

// DTO -  data transfer object -> a design pattern used to pass the data with
// multiple params from client to server in one shot (to avoid remote calls or server calls)
// the main advantage of using it is that the implementation details of JPA entities can be hidden