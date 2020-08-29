package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.models.Author;
import ru.otus.library.dto.AuthorDto;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional
    public void createAuthor(AuthorDto authorDto) {
        authorRepository.save(new Author(0,
                                authorDto.getFirstName(),
                                authorDto.getMiddleName(),
                                authorDto.getLastName()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Author> findByIdIn(Set<Long> authorIdList) {
        return authorRepository.findByIdIn(authorIdList);
    }
}
