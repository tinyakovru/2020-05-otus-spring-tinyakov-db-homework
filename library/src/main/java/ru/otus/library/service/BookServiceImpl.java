package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthorId(long authorId) {
        return bookRepository.getByAuthorId(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByGenreId(long genreId) {
        return bookRepository.getByGenreId(genreId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookWithComments(long bookId) {
        return bookRepository.getBookByIdWithComments(bookId);
    }

    @Override
    @Transactional
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

}
