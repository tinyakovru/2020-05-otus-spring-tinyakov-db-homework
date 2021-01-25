package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.library.models.Author;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("тестируем BookRepository")
class BookRepositoryTest extends AbstractRepositoryTest  {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository aRepository;
    @Autowired
    MongoTemplate template;

    @DisplayName("BookRepository должен возвращать все книги")
    @Test
    void shouldFindAllBooks(){
        var books = bookRepository.findAll();
        assertThat(books).isNotNull().hasSize(3);
    }

    @DisplayName("BookRepository должен возвращать книги по автору")
    @Test
    void shouldFindBooksByAuthor(){
        var books = bookRepository.findByAuthor("Петров");
        assertThat(books).isNotNull().hasSize(2);
    }

    @DisplayName("BookRepository должен возвращать книги по автору")
    @Test
    void shouldFindBooksByAuthor2(){
        var books = bookRepository.findByAuthor("Пушкин");
        assertThat(books).isNotNull().hasSize(1);
    }

    @DisplayName("BookRepository должен возвращать книги по жанру")
    @Test
    void shouldFindBooksByGenre(){
        var books = bookRepository.findByGenres("поэзия");
        assertThat(books).isNotNull().hasSize(1);
    }
}