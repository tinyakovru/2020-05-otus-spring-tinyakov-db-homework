package ru.otus.library.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.library.repositories.BookRepository;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@Import(BookServiceImpl.class)
@DisplayName("тестируем класс BookServiceImpl")
class BookServiceImplTest {
    @MockBean
    private BookRepository dao;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    @DisplayName("getBookById должен вызыват ьсоответствующий метод ДАО слоя")
    void testGetBookById() {
        bookService.getBookById(3);
        verify(dao, times(1)).findById(3L);
    }

    @Test
    @DisplayName("getAllBooks должен вызыват ьсоответствующий метод ДАО слоя")
    void testGetAllBooks() {
        bookService.getAllBooks();
        verify(dao, times(1)).findAll();
    }

    @Test
    @DisplayName("должен вызывать соответствующий метод ДАО слоя")
    void deleteBookById() {
        bookService.deleteBookById(50);
        verify(dao, times(1)).deleteById(50L);
    }
}