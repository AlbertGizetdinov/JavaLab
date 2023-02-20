package ru.itis.controllers;

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
import ru.itis.dto.AddToFavoritesDto;
import ru.itis.dto.AuthorDto;
import ru.itis.dto.AuthorsPage;
import ru.itis.services.AuthorsService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/authors")
public class AuthorsController {

    private final AuthorsService authorsService;

    @Operation(summary = "Getting authors with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authors page",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorsPage.class))})})
    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(@Parameter(description = "Page number") @RequestParam("page") int page) {
        return ResponseEntity.ok(authorsService.getAuthors(page));
    }

    @Operation(summary = "Add author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Page with successfully added author",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = AuthorDto.class))})})
    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@Valid @RequestBody AuthorDto author) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(author));
    }

    @Operation(summary = "Update author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Page with successfully updated author",
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

    @Operation(summary = "Delete author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Author deleted")})
    @DeleteMapping("/{author-id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("author-id") Long authorId) {
        authorsService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @Operation(summary = "Adding a post to author favorites")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post added to favorites")})
    @PostMapping("/{author-id}/favorites")
    public ResponseEntity<?> addPostToFavorites(@PathVariable("author-id") Long authorId,
                                                @RequestBody AddToFavoritesDto post) {
        authorsService.addPostToFavorites(authorId, post);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "Delete a post from a favorites author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Post deleted from favorites")})
    @DeleteMapping("/{author-id}/favorites/{post-id}")
    public ResponseEntity<?> deletePostFromFavorites(@PathVariable("author-id") Long authorId,
                                                     @PathVariable("post-id") Long postId) {
        authorsService.deletePostFromFavorites(authorId, postId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED).build();
    }
}
