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
        authorRepository.save(new Author("",
                authorDto.getFirstName(),
                authorDto.getMiddleName(),
                authorDto.getLastName()));
    }

    @Override
    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findByIdIn(Set<String> authorIdList) {
        List<Author> list = new ArrayList<>();
//        Iterable<Author> iterable = authorRepository.findAllById(authorIdList);
//        for(Author a : iterable){
//            set.add(a);
//        }
        authorRepository.findAllById(authorIdList).forEach(list::add);
        return list;
    }
}
