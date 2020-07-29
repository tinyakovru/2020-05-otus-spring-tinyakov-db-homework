package ru.otus.library.service;

import ru.otus.library.models.Genre;

import javax.persistence.GeneratedValue;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GenreService {
    void create(Genre genre);
    List<Genre> getAllGenres();
    Optional<Genre> findById(long id);
    Set<Genre> findByIds(Set<Long> genresIdList);
}
