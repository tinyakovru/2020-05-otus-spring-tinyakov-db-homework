package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.dao.BookDao;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.BookDto;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookDao bookDao;

    @Override
    public Book getBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public List<Book> getBooksByAuthor(long authorId) {
        return bookDao.getByAuthor(authorId);
    }

    @Override
    public List<Book> getBooksByGenre(long genreId) {
        return bookDao.getByGenre(genreId);
    }

    @Override
    public void createBook(BookDto bookDto) throws Exception {
        bookDao.createBook(bookDto);
    }

    @Override
    public void updateBook(long bookId, String title, Set<Long> authorIds, Set<Long> genreIds) {
        bookDao.updateBook(new BookDto(bookId,title,authorIds,genreIds));

    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }

}
