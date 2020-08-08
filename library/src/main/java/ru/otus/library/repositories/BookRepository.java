package ru.otus.library.repositories;

import ru.otus.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> getById(long id);

    List<Book> getAllBooks();

    List<Book> getByAuthorId(long authorId);

    List<Book> getByGenreId(long genreId);

    void deleteById(long id);
}
