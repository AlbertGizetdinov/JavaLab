package ru.itis.blog.validation.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Validation error")
public class ValidationErrorDto {
    @Schema(description = "Field", example = "\"firstName\"")
    private String field;
    @Schema(description = "Object", example = "\"authorDto\"")
    private String object;
    @Schema(description = "Message", example = "\"The minimum name size is 2, the maximum is 20\"")
    private String message;
}
