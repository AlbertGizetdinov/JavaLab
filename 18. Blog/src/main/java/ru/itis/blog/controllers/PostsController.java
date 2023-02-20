package ru.itis.blog.controllers;

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

    @GetMapping
    public ResponseEntity<PostsPage> getPosts(@RequestParam("page") int page) {
        return ResponseEntity.ok(postsService.getPosts(page));
    }

    @GetMapping("/{author-id}")
    public ResponseEntity<PostsPage> getPosts(@PathVariable("author-id") Long authorId,
                                              @RequestParam("page") int page) {
        return ResponseEntity.ok(postsService.getPosts(authorId, page));
    }

    @PostMapping
    public ResponseEntity<PostDto> addPost(@RequestBody PostDto post) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(postsService.addPost(post));
    }

    @PutMapping("/{post-id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("post-id") Long postId,
                                              @RequestBody PostDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(postsService.updatePost(postId, newData));
    }

    @DeleteMapping("/{post-id}")
    public ResponseEntity deletePost(@PathVariable("post-id") Long postId) {
        postsService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
