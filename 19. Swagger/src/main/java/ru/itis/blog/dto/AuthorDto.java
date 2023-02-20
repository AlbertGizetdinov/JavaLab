package ru.itis.blog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.blog.models.Author;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Автор")
public class AuthorDto {
    @Schema(description = "Идентификатор", example = "1")
    private Long id;
    @Schema(description = "Имя", example = "Альберт")
    private String firstName;
    @Schema(description = "Фамилия", example = "Гизетдинов")
    private String lastName;

    public static AuthorDto from(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build();
    }

    public static List<AuthorDto> from(List<Author> authors) {
        return authors.stream().map(AuthorDto::from).collect(Collectors.toList());
    }
}
