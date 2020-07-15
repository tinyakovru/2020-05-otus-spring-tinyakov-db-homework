package ru.otus.library.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.AuthorDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
@ExtendWith(SpringExtension.class)
@DisplayName("Тестируем класс AuthorDao")
class AuthorDaoTest {

    @Autowired
    private AuthorDaoJdbc dao;

    @Test
    @DisplayName("Должен добавлять в таблицу нового автора")
    void shouldInsertAuthor() {
        AuthorDto authorDto = new AuthorDto("Артур", "", "Конандойль");
        dao.insertAuthor(authorDto);

        List<Author> authors = dao.getAll();
        Author author = authors.get(authors.size() - 1);

        assertEquals(8, author.getId());
        assertEquals("Артур", author.getFirstName());
        assertEquals("Конандойль", author.getLastName());
        assertEquals("", author.getMiddleName());
    }

    @Test
    @DisplayName("должен возвращать список всех авторов из таблицы")
    void shouldGetAllAuthors() {
        AuthorDto authorDto = new AuthorDto("Артур", "", "Конандойль");
        List<Author> authors = dao.getAll();

        assertEquals(7, authors.size());
        assertEquals("Петров",authors.get(6).getLastName());
        assertEquals("Агата",authors.get(3).getFirstName());
    }
}