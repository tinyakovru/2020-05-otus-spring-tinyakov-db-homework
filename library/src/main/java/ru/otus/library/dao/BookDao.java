package ru.otus.library.dao;

import ru.otus.library.domain.Book;
import ru.otus.library.domain.BookDto;

import java.util.List;

public interface BookDao {
    Book getById(long id);
    List<Book> getByAuthor(long authorId);
    List<Book> getByGenre(long genreId);
    List<Book> getAll();
    long createBook(BookDto bookDto);
    void updateBook(BookDto bookDto);
    void deleteById(long id);
}
