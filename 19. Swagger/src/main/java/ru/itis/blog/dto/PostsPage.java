package ru.itis.blog.dto;

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
@Schema(description = "Страница со списком постов и общее количество таких страниц")
public class PostsPage {

    private List<PostDto> posts;
    @Schema(description = "Количество доступных страниц",  example = "5")
    private Integer totalPages;
}
