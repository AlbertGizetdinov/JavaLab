package ru.itis.blog.services;

import ru.itis.blog.dto.AddToFavoritesDto;
import ru.itis.blog.dto.AuthorDto;
import ru.itis.blog.dto.AuthorsPage;

public interface AuthorsService {
    AuthorsPage getAuthors(int page);

    AuthorDto addAuthor(AuthorDto author);

    AuthorDto updateAuthor(Long authorId, AuthorDto newData);

    void deleteAuthor(Long authorId);

    void addPostToFavorites(Long authorId, AddToFavoritesDto post);

    void deletePostFromFavorites(Long authorId, Long postId);

}
