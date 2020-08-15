package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Genre;

import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre,Long> {
    Set<Genre> findByIdIn(Set<Long> ids);
}
