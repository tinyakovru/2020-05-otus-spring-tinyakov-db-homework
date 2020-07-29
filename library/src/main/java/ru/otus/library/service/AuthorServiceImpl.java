package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.models.Author;
import ru.otus.library.dto.AuthorDto;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public void createAuthor(AuthorDto authorDto) {
        authorRepository.save(new Author(0,
                                authorDto.getFirstName(),
                                authorDto.getMiddleName(),
                                authorDto.getLastName()));
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Set<Author> findByIds(Set<Long> authorIdList) {
        return authorRepository.findByIds(authorIdList);
    }
}
