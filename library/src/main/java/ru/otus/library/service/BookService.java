package ru.otus.library.service;

import ru.otus.library.domain.Book;
import ru.otus.library.domain.BookDto;

import java.util.List;
import java.util.Set;

public interface BookService {
    Book getBookById(long id);
    List<Book> getAllBooks();
    List<Book> getBooksByAuthor(long authorId);
    List<Book> getBooksByGenre(long genreId);
    void createBook(BookDto bookDto) throws Exception;
    void updateBook(long bookId, String title, Set<Long> authorIds, Set<Long> genreIds);
    void deleteBookById(long id);

}
