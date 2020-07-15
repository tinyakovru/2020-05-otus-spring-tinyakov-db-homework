package ru.otus.library.dao;

import ru.otus.library.domain.Genre;

import java.util.List;

public interface GenreDao {
    void create(String title);
    List<Genre> getAll();
}
