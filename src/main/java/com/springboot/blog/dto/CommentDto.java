package com.springboot.blog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    //name should not be null or empty
    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    //email should not be null or empty
    //email field validation
    @NotEmpty(message = "email should not be null or empty")
    @Email
    private String email;

    //comment body should not be null or empty
    //comment body must be minimum 10 character
    @NotEmpty
    @Size(min = 10, message = "comment body must be minimum 10 character")
    private String body;
}
