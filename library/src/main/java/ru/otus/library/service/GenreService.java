package ru.otus.library.service;

import ru.otus.library.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GenreService {
    void create(Genre genre);
    List<Genre> getAllGenres();
    Optional<Genre> findById(String id);
    Set<Genre> findByIdIn(Set<String> genresIdList);
}
