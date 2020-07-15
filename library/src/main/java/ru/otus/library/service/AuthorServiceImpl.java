package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.AuthorDao;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.AuthorDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public void createAuthor(AuthorDto authorDto) {
        authorDao.insertAuthor(authorDto);
    }

    @Override
    public List<Author> getAuthors() {
        return authorDao.getAll();
    }
}
