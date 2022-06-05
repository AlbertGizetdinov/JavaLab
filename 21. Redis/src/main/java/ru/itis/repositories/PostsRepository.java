package ru.itis.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Post;

public interface PostsRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByAuthor_Id(Pageable pageable, Long authorId);
}
