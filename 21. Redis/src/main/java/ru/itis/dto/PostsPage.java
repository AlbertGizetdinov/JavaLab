package ru.itis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Page with the list of posts and the total number of such pages")
public class PostsPage {
    @Schema(description = "Posts list")
    private List<PostDto> posts;
    @Schema(description = "Number of available pages",  example = "5")
    private Integer totalPages;
}
