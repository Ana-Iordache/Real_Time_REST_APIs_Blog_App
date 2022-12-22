package com.blog.blogrestapi.payload;

import lombok.Data;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
}

// DTO -  data transfer object -> a design pattern used to pass the data with
// multiple params from client to server in one shot (to avoid remote calls or server calls)
// the main advantage of using it is that the implementation details of JPA entities can be hidden