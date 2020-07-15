package ru.otus.library.dao;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.AuthorDto;

import java.util.List;

public interface AuthorDao {
    void insertAuthor(AuthorDto authorDto);
    List<Author> getAll();
}
