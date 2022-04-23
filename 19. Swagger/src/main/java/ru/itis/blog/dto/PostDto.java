package ru.itis.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Пост")
public class PostDto {
    @Schema(description = "Идентификатор", example = "1")
    private Long id;
    @Schema(description = "Дата создания", example = "2022-04-23T13:34:38.413")
    private LocalDateTime createdAt;
    @Schema(description = "Заголовок", example = "Заголовок")
    private String title;
    @Schema(description = "Текст", example = "Какой-то текст")
    private String text;
    @Schema(description = "Состояние", example = "PUBLISHED")
    private Post.State state;
    @Schema(description = "Идентификатор автора", example = "1")
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
