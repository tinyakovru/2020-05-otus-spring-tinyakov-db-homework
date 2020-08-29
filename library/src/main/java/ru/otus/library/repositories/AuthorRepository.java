package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Author;

import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Set<Author> findByIdIn(Set<Long> ids);
}
