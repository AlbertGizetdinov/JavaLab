package ru.itis.blog.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.blog.dto.AddToFavoritesDto;
import ru.itis.blog.dto.AuthorDto;
import ru.itis.blog.dto.AuthorsPage;
import ru.itis.blog.services.AuthorsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private final AuthorsService authorsService;

    @Operation(summary = "Получение авторов с пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с авторами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorsPage.class))})})
    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(@Parameter(description = "Номер страницы") @RequestParam("page") int page) {
        return ResponseEntity.ok(authorsService.getAuthors(page));
    }

    @Operation(summary = "Добавление автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Страница с успешно созданным автором",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorDto.class))})})
    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto author) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(author));
    }

    @Operation(summary = "Обновление автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Страница с успешно обновленным автором",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorDto.class))})})
    @PutMapping("/{author-id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("author-id") Long authorId,
                                                  @RequestBody AuthorDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.updateAuthor(authorId, newData));
    }

    @Operation(summary = "Удаление автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Автор удален")})
    @DeleteMapping("/{author-id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("author-id") Long authorId) {
        authorsService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Operation(summary = "Добавление поста в избранное автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пост добавлен в избранное")})
    @PostMapping("/{author-id}/favorites")
    public ResponseEntity<?> addPostToFavorites(@PathVariable("author-id") Long authorId,
                                                @RequestBody AddToFavoritesDto post) {
        authorsService.addPostToFavorites(authorId, post);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "Удаление поста из избранного автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Пост удален из избранного")})
    @DeleteMapping("/{author-id}/favorites/{post-id}")
    public ResponseEntity<?> deletePostFromFavorites(@PathVariable("author-id") Long authorId,
                                                     @PathVariable("post-id") Long postId) {
        authorsService.deletePostFromFavorites(authorId, postId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
