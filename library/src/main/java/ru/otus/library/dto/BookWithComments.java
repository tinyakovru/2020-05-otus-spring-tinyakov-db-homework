package ru.otus.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;

import java.util.List;

@Data
@AllArgsConstructor
public class BookWithComments {
    private Book book;
    private List<Comment> comments;
}
