package ru.itis.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Add to favorites")
public class AddToFavoritesDto {
    @Schema(description = "Post identifier", example = "3")
    private Long postId;
}
