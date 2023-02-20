package ru.itis.services;

import ru.itis.dto.PostDto;
import ru.itis.dto.PostsPage;

public interface PostsService {
    PostsPage getPosts(int page);

    PostsPage getPosts(Long authorId, int page);

    PostDto addPost(PostDto post);

    PostDto updatePost(Long postId, PostDto newData);

    void deletePost(Long postId);
}
