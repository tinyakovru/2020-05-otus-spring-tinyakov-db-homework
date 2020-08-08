package ru.otus.library.repositories;

import ru.otus.library.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GenreRepository {
    Genre save(Genre genre);
    Optional<Genre> findById(long id);
    List<Genre> findAll();
    Set<Genre> findByIds(Set<Long> ids);

}
