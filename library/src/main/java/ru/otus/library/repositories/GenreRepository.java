package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.library.models.Genre;

import java.util.Set;

public interface GenreRepository extends MongoRepository<Genre,String> {
    Genre findByTitle(String genre);
}
