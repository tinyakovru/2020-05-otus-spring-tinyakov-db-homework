package ru.otus.library.service;

import ru.otus.library.dto.BookWithComments;
import ru.otus.library.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> getBookById(String id);
    List<Book> getAllBooks();
    List<Book> getBooksByAuthorLastName(String authorLastName);
    List<Book> getBooksByGenre(String genre);
    BookWithComments getBookWithComments(String bookId);
    Book createBook(Book book);
    Book updateBook(Book book);
    void deleteBookById(String id);

}
