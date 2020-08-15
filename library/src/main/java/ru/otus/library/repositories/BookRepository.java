package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

//    List<Book> findAll();
//    Book save(Book book);
//
//    Optional<Book> getById(long id);
//
//    List<Book> getAllBooks();
//
    List<Book> findByAuthors(Author author);
//
    List<Book> findByGenres(Genre genre);
//
//    void deleteById(long id);
}
