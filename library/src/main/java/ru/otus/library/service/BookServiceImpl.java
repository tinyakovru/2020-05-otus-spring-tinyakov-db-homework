package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dto.BookWithComments;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.models.Book;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Optional<Book> getBookById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    public List<Book> getBooksByAuthorId(long authorId) {
        return bookRepository.getByAuthorId(authorId);
    }

    @Override
    public List<Book> getBooksByGenreId(long genreId) {
        return bookRepository.getByGenreId(genreId);
    }

    @Override
    public Optional<Book> getBookWithComments(long bookId) {
        return bookRepository.getBookByIdWithComments(bookId);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

}
