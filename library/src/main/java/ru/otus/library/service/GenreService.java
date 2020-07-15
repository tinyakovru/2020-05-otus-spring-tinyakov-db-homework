package ru.otus.library.service;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreService {
    void create(String title);
    List<Genre> getGenres();
}
