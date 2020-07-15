package ru.otus.library.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестируем класс Book")
class BookTest {

    @Test
    @DisplayName("Проверяем работу конструктора")
    void shouldCorrectCreateBook(){
        Set<Author> ids1 = new HashSet<>();
        Set<Genre> ids2 = new HashSet<>();
        Book book = new Book(1,ids1,ids2,"titlebook");
        assertEquals("titlebook",book.getTitle());
        assertEquals(1,book.getId());
    }
}