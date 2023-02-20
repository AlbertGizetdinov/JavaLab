package ru.itis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Post;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Post")
public class PostDto {
    @Schema(description = "Identifier", example = "1")
    private Long id;
    @Schema(description = "Date of creation", example = "2022-04-23T13:34:38.413")
    private LocalDateTime createdAt;
    @Size(min = 5, max = 30, message = "The minimum title size is {min}, the maximum is {max}")
    @Schema(description = "Title", example = "Incredible title")
    private String title;
    @Size(min = 5, max = 1000, message = "The minimum text size is {min}, the maximum is {max}")
    @Schema(description = "Text", example = "Lots of cool text")
    private String text;
    @Schema(description = "State", example = "PUBLISHED")
    private Post.State state;
    @Schema(description = "Author identifier", example = "1")
    private Long authorId;

    public static PostDto from(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .createdAt(post.getCreatedAt())
                .title(post.getTitle())
                .text(post.getText())
                .state(post.getState())
                .authorId(post.getAuthor().getId())
                .build();
    }

    public static List<PostDto> from(List<Post> posts) {
        return posts.stream().map(PostDto::from).collect(Collectors.toList());
    }
}
