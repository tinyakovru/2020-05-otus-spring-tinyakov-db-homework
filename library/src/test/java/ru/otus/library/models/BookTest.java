package ru.otus.library.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестируем класс Book")
class BookTest {

    @Test
    @DisplayName("Проверяем работу конструктора")
    void shouldCorrectCreateBook(){
        List<Author> ids1 = new ArrayList<>();
        Set<Genre> ids2 = new HashSet<>();
        Book book = new Book("id111",ids1,ids2,"titlebook");
        assertEquals("titlebook",book.getTitle());
        assertEquals("id111",book.getId());
    }
}