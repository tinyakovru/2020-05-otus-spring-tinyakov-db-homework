package ru.otus.library.service;

import ru.otus.library.domain.Author;
import ru.otus.library.domain.AuthorDto;

import java.util.List;

public interface AuthorService {
    void createAuthor(AuthorDto authorDto);

    List<Author> getAuthors();
}
