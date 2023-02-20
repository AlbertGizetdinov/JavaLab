package ru.itis.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Добавление в избранное")
public class AddToFavoritesDto {
    @Schema(description = "Идентификатор поста", example = "3")
    private Long postId;
}
