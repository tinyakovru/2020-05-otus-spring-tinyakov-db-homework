package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Author;
import ru.otus.library.repositories.GenreRepository;
import ru.otus.library.models.Genre;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public void create(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(String id) {
        return genreRepository.findById(id);
    }

    @Override
    public Set<Genre> findByIdIn(Set<String> genresIdList) {
        Set<Genre> set = new HashSet<>();
//        Iterable<Author> iterable = authorRepository.findAllById(authorIdList);
//        for(Author a : iterable){
//            set.add(a);
//        }
        genreRepository.findAllById(genresIdList).forEach(set::add);
        return set;
//        return genreRepository.findByIdIn(genresIdList);
    }
}
