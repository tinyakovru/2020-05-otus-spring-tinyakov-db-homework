package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Author;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.models.Book;
import ru.otus.library.repositories.GenreRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthorId(long authorId) {
        return bookRepository.findByAuthors(authorRepository.findById(authorId).get());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByGenreId(long genreId) {
        return bookRepository.findByGenres(genreRepository.findById(genreId).get());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookWithComments(long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        optionalBook.get().getComments().size();
        return optionalBook;
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
