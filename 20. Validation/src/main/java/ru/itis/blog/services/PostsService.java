package ru.itis.blog.services;

import ru.itis.blog.dto.PostDto;
import ru.itis.blog.dto.PostsPage;

public interface PostsService {
    PostsPage getPosts(int page);

    PostsPage getPosts(Long authorId, int page);

    PostDto addPost(PostDto post);

    PostDto updatePost(Long postId, PostDto newData);

    void deletePost(Long postId);
}
