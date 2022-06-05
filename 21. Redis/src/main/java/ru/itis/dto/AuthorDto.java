package ru.itis.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Author;
import ru.itis.validation.annotations.NotSameNames;

import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Author")
@NotSameNames(names = {"firstName", "lastName"}, message = "{names} are same")
public class AuthorDto {
    @Schema(description = "Identifier", example = "1")
    private Long id;

    @Size(min = 2, max = 20, message = "The minimum first name size is {min}, the maximum is {max}")
    @Schema(description = "First name", example = "Albert")
    private String firstName;

    @Size(min = 2, max = 20, message = "The minimum last name size is {min}, the maximum is {max}")
    @Schema(description = "Last name", example = "Gizetdinov")
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
