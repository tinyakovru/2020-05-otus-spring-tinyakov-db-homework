package ru.otus.library.service;

import ru.otus.library.models.Author;
import ru.otus.library.dto.AuthorDto;

import java.util.List;
import java.util.Set;

public interface AuthorService {
    void createAuthor(AuthorDto authorDto);

    List<Author> getAuthors();

    List<Author> findByIdIn(Set<String> authorIdList);
}
