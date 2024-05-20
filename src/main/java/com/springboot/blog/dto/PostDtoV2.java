package com.springboot.blog.dto;

import com.springboot.blog.entity.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PostDtoV2 {
    private long id;

    @Schema(
            description = "Post Post Title"
    )
    //title should not be null or empty
    //title should  have at least 2 characters
    @NotEmpty
    @Size(min = 2,message = "Post title should have at least 2 characters")
    private String title;

    @Schema(
            description = "Blog Post Description"
    )
    //description should not be null or empty
    //description should  have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 characters")
    private String description;

    @Schema(
            description = "Blog Post Content"
    )
    //content should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    @Schema(
            description = "Blog Post Category"
    )
    private Long categoryId;

    private List<String> tags;
}
