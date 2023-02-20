package ru.itis.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.dto.AddToFavoritesDto;
import ru.itis.dto.AuthorDto;
import ru.itis.dto.AuthorsPage;
import ru.itis.exceptions.AuthorNotExistsException;
import ru.itis.exceptions.AuthorNotFoundException;
import ru.itis.exceptions.PostNotExistsException;
import ru.itis.models.Author;
import ru.itis.models.Post;
import ru.itis.repositories.AuthorsRepository;
import ru.itis.repositories.PostsRepository;
import ru.itis.services.AuthorsService;

import java.util.function.Supplier;

import static ru.itis.dto.AuthorDto.from;

@RequiredArgsConstructor
@Service
public class AuthorsServiceImpl implements AuthorsService {

    private final AuthorsRepository authorsRepository;

    private final PostsRepository postsRepository;

    @Value("${blog.default-page-size}")
    private int defaultPageSize;

    @Override
    public AuthorsPage getAuthors(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by("firstName"));
        Page<Author> authorPage = authorsRepository.findAll(pageRequest);
        return AuthorsPage.builder()
                .authors(AuthorDto.from(authorPage.getContent()))
                .totalPages(authorPage.getTotalPages())
                .build();
    }

    @Override
    public AuthorDto addAuthor(AuthorDto author) {
        return AuthorDto.from(authorsRepository.save(Author.builder()
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .build()));
    }

    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto newData) {
        Author author = authorsRepository.findById(authorId).orElseThrow(AuthorNotFoundException::new);

        author.setFirstName(newData.getFirstName());
        author.setLastName(newData.getLastName());

        return AuthorDto.from(authorsRepository.save(author));
    }

    @Override
    public void deleteAuthor(Long authorId) {
        Author author = authorsRepository.findById(authorId).orElseThrow(AuthorNotFoundException::new);
        authorsRepository.delete(author);
    }

    @Override
    public void addPostToFavorites(Long authorId, AddToFavoritesDto post) {
        Author author = authorsRepository
                .findById(authorId)
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new AuthorNotExistsException(authorId));

        Post favoritePost = postsRepository
                .findById(post.getPostId())
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new PostNotExistsException(post.getPostId()));

        author.getFavorites().add(favoritePost);

        authorsRepository.save(author);
    }

    @Override
    public void deletePostFromFavorites(Long authorId, Long postId) {
        Author author = authorsRepository
                .findById(authorId)
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new AuthorNotExistsException(authorId));

        Post favoritePost = postsRepository
                .findById(postId)
                .orElseThrow((Supplier<RuntimeException>) ()
                        -> new PostNotExistsException(postId));

        author.getFavorites().remove(favoritePost);

        authorsRepository.save(author);
    }
}
