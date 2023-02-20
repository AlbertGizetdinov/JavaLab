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
import ru.itis.blog.dto.PostDto;
import ru.itis.blog.dto.PostsPage;
import ru.itis.blog.services.PostsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @Operation(summary = "Получение постов с пагинацией")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с постами",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostsPage.class))})})
    @GetMapping
    public ResponseEntity<PostsPage> getPosts(@Parameter(description = "Номер страницы") @RequestParam("page") int page) {
        return ResponseEntity.ok(postsService.getPosts(page));
    }

    @Operation(summary = "Получение постов с пагинацией определенного автора")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с постами автора",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostsPage.class))})})
    @GetMapping("/{author-id}")
    public ResponseEntity<PostsPage> getPosts(@PathVariable("author-id") Long authorId,
                                              @Parameter(description = "Номер страницы") @RequestParam("page") int page) {
        return ResponseEntity.ok(postsService.getPosts(authorId, page));
    }

    @Operation(summary = "Добавление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Страница с успешно созданным постом",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class))})})
    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto post) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.addPost(post));
    }

    @Operation(summary = "Обновление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Страница с успешно обновленным постом",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class))})})
    @PutMapping("/{post-id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("post-id") Long postId,
                                              @RequestBody PostDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postsService.updatePost(postId, newData));
    }

    @Operation(summary = "Удаление поста")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Пост удален")})
    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> deletePost(@PathVariable("post-id") Long postId) {
        postsService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
