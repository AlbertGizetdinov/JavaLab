package ru.itis.blog.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.blog.dto.AuthorDto;
import ru.itis.blog.dto.AuthorsPage;
import ru.itis.blog.services.AuthorsService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authors")
public class AuthorsController {

    private final AuthorsService authorsService;

    @GetMapping
    public ResponseEntity<AuthorsPage> getAuthors(@RequestParam("page") int page) {
        return ResponseEntity.ok(authorsService.getAuthors(page));
    }

    @PostMapping
    public ResponseEntity<AuthorDto> addAuthor(@RequestBody AuthorDto author) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authorsService.addAuthor(author));
    }

    @PutMapping("/{author-id}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable("author-id") Long authorId,
                                                  @RequestBody AuthorDto newData) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(authorsService.updateAuthor(authorId, newData));
    }

    @DeleteMapping("/{author-id}")
    public ResponseEntity deleteAuthor(@PathVariable("author-id") Long authorId) {
        authorsService.deleteAuthor(authorId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
