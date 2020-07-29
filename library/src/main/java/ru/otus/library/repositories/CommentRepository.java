package ru.otus.library.repositories;

import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);

    Comment getById(long id);

    List<Comment> getByBookId(long bookId);

    void delete(long id);
}
