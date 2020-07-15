package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.BookDto;
import ru.otus.library.domain.Genre;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестируем класс BookDaoJdbc")
@Import(BookDaoJdbc.class)
@JdbcTest
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc dao;

    @DisplayName("должен возвращать книгу по id со списком авторов и жанров")
    @Test
    void shouldGetById() {
        Book book = dao.getById(6);
        assertEquals("Что такое хорошо и что такое плохо", book.getTitle());

        Set<Genre> genres = book.getGenres();
        assertEquals(2, genres.size());
        assertTrue(genres.contains(new Genre(1, "детская литература")));

        Set<Author> authors = book.getAuthors();
        assertEquals(1, authors.size());
        assertTrue(authors.contains(new Author(3, "Владимир", "Владимирович", "Маяковский")));
    }

    @DisplayName("Должен возвращать все книги по id автора")
    @Test
    void shouldGetByAuthor() {
        List<Book> books = dao.getByAuthor(6);
        assertEquals(2, books.size());
        assertEquals(13, books.get(0).getId());
        assertEquals("Золотой телёнок", books.get(1).getTitle());
    }

    @DisplayName("Должен возвращать все книги по id жанра")
    @Test
    void shouldGetByGenre() {
        List<Book> books = dao.getByGenre(3);
        assertEquals(6, books.size());
        assertEquals(7, books.get(5).getId());
        assertEquals("Сказка о попе и работнике его балде", books.get(0).getTitle());
    }

    @DisplayName("Должен возвращать все книги")
    @Test
    void shouldGetAll() {
        List<Book> books = dao.getAll();
        assertEquals(14, books.size());
    }

    @DisplayName("Должен создавать книгу")
    @Test
    void shouldCreateBook() {
        Set<Long> ids = new HashSet<>();
        ids.add(2l);
        ids.add(3l);
        dao.createBook(new BookDto(0, "newBookTitle", ids,ids));

        Book book = dao.getById(15);
        assertEquals("newBookTitle", book.getTitle());

        Set<Genre> genres = book.getGenres();
        assertEquals(2, genres.size());
        assertTrue(genres.contains(new Genre(2, "проза")));
        assertTrue(genres.contains(new Genre(3, "поэзия")));

        Set<Author> authors = book.getAuthors();
        assertEquals(2, authors.size());
        assertTrue(authors.contains(new Author(3, "Владимир", "Владимирович", "Маяковский")));
        assertTrue(authors.contains(new Author(2, "Сергей", "Александрович",  "Есенин")));
    }

    @DisplayName("должен изменять книгу")
    @Test
    void shouldUpdateBook() {
        Set<Long> ids = new HashSet<>();
        ids.add(2l);
        ids.add(3l);
        dao.updateBook(new BookDto(14,"updated name",ids,ids));

        Book book = dao.getById(14);
        assertEquals("updated name", book.getTitle());

        Set<Genre> genres = book.getGenres();
        assertEquals(2, genres.size());
        assertTrue(genres.contains(new Genre(2, "проза")));
        assertTrue(genres.contains(new Genre(3, "поэзия")));

        Set<Author> authors = book.getAuthors();
        assertEquals(2, authors.size());
        assertTrue(authors.contains(new Author(3, "Владимир", "Владимирович", "Маяковский")));
        assertTrue(authors.contains(new Author(2, "Сергей", "Александрович",  "Есенин")));
    }

    @DisplayName("должен удалять книгу")
    @Test
    void shouldDeleteById() {
        dao.deleteById(2);
        List<Book> books = dao.getAll();
        assertEquals(13, books.size());
        assertEquals(3,books.get(1).getId());
    }
}
