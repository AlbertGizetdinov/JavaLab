package ru.itis.repositories;

import ru.itis.models.NewFile;

import java.util.List;
import java.util.Optional;

public interface FileRepository {
    List<NewFile> findAll();

    void save(NewFile file);

    Optional<NewFile> findByUuidName(String uuidName);
}
