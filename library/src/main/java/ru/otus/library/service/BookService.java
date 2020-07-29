package ru.otus.library.service;

import ru.otus.library.dto.BookWithComments;
import ru.otus.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getBookById(long id);
    List<Book> getAllBooks();
    List<Book> getBooksByAuthorId(long authorId);
    List<Book> getBooksByGenreId(long genreId);
    Optional<Book> getBookWithComments(long bookId);
    Book createBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(long id);

}
