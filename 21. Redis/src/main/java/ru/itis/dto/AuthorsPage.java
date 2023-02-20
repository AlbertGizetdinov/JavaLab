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
@Schema(description = "Page with the list of authors and the total number of such pages")
public class AuthorsPage {
    @Schema(description = "Authors list")
    private List<AuthorDto> authors;
    @Schema(description = "Number of available pages", example = "4")
    private Integer totalPages;
}
