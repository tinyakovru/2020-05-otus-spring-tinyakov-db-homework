package ru.otus.library.repositories;

import ru.otus.library.models.Author;
import ru.otus.library.dto.AuthorDto;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findById(long id);
    List<Author> findAll();
    Set<Author> findByIds(Set<Long> ids);
}
