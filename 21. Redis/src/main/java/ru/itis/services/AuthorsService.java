package ru.itis.services;

import ru.itis.dto.AddToFavoritesDto;
import ru.itis.dto.AuthorDto;
import ru.itis.dto.AuthorsPage;

public interface AuthorsService {
    AuthorsPage getAuthors(int page);

    AuthorDto addAuthor(AuthorDto author);

    AuthorDto updateAuthor(Long authorId, AuthorDto newData);

    void deleteAuthor(Long authorId);

    void addPostToFavorites(Long authorId, AddToFavoritesDto post);

    void deletePostFromFavorites(Long authorId, Long postId);

}
