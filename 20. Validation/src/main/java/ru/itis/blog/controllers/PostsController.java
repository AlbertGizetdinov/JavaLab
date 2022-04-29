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

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostsController {

    private final PostsService postsService;

    @Operation(summary = "Getting posts with pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with posts",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostsPage.class))})})
    @GetMapping
    public ResponseEntity<PostsPage> getPosts(@Parameter(description = "Page number") @RequestParam("page") int page) {
        return ResponseEntity.ok(postsService.getPosts(page));
    }

    @Operation(summary = "Getting posts with pagination of a specific author")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Page with author's posts",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostsPage.class))})})
    @GetMapping("/{author-id}")
    public ResponseEntity<PostsPage> getPosts(@PathVariable("author-id") Long authorId,
                                              @Parameter(description = "Page number") @RequestParam("page") int page) {
        return ResponseEntity.ok(postsService.getPosts(authorId, page));
    }

    @Operation(summary = "Add post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Page with successfully added post",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema =
                                    @Schema(implementation = PostDto.class))})})
    @PostMapping
    public ResponseEntity<PostDto> addPost(@Valid @RequestBody PostDto post) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.addPost(post));
    }

    @Operation(summary = "Update post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Page with successfully updated post",
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

    @Operation(summary = "Delete post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Post deleted")})
    @DeleteMapping("/{post-id}")
    public ResponseEntity<?> deletePost(@PathVariable("post-id") Long postId) {
        postsService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
