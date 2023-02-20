package ru.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.models.Author;

public interface AuthorsRepository extends JpaRepository<Author, Long> {
}
