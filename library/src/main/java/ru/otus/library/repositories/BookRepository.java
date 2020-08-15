package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Genre;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByAuthors(Author author);
    List<Book> findByGenres(Genre genre);
}
