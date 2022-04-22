package ru.itis.blog.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.itis.blog.dto.PostDto;
import ru.itis.blog.dto.PostsPage;
import ru.itis.blog.exceptions.AuthorNotFoundException;
import ru.itis.blog.exceptions.PostNotFoundException;
import ru.itis.blog.models.Post;
import ru.itis.blog.repositories.AuthorsRepository;
import ru.itis.blog.repositories.PostsRepository;
import ru.itis.blog.services.PostsService;

import java.time.LocalDateTime;

import static ru.itis.blog.dto.PostDto.from;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostsRepository postsRepository;
    private final AuthorsRepository authorsRepository;

    @Value("${blog.default-page-size}")
    private int defaultPageSize;

    @Override
    public PostsPage getPosts(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by("author_id", "id"));
        Page<Post> postPage = postsRepository.findAll(pageRequest);
        return PostsPage.builder()
                .posts(from(postPage.getContent()))
                .totalPages(postPage.getTotalPages())
                .build();
    }

    @Override
    public PostsPage getPosts(Long authorId, int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize, Sort.by("createdAt"));
        Page<Post> postPage = postsRepository.findAllByAuthor_Id(pageRequest, authorId);
        return PostsPage.builder()
                .posts(from(postPage.getContent()))
                .totalPages(postPage.getTotalPages())
                .build();
    }

    @Override
    public PostDto addPost(PostDto post) {
        return from(postsRepository.save(Post.builder()
                .createdAt(LocalDateTime.now())
                .title(post.getTitle())
                .text(post.getText())
                .state(post.getState())
                .author(authorsRepository.findById(post.getAuthorId()).orElseThrow(AuthorNotFoundException::new))
                .build()));
    }

    @Override
    public PostDto updatePost(Long postId, PostDto newData) {
        Post post = postsRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        post.setCreatedAt(LocalDateTime.now());
        post.setTitle(newData.getTitle());
        post.setText(newData.getText());
        post.setState(newData.getState());
        post.setAuthor(authorsRepository.getById(newData.getAuthorId()));

        return from(postsRepository.save(post));
    }

    @Override
    public void deletePost(Long postId) {
        Post post = postsRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        postsRepository.delete(post);
    }
}
