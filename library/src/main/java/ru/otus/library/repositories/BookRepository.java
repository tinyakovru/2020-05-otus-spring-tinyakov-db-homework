package ru.otus.library.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;

import java.util.List;

public interface BookRepository extends MongoRepository<Book,String> {

    @Query(value = "{ 'authors.last_name' : {$all : [?0] }}")
    List<Book> findByAuthor(String authorLastName);

    @Query(value = "{ 'genres.title' : {$all : [?0] }}")
    List<Book> findByGenres(String genre);
}
