package ru.otus.library.service;

import ru.otus.library.models.Comment;

import java.util.Optional;

public interface CommentService {
    Comment create(String bookId, String text);
    Comment save(Comment comment);
    Optional<Comment> getById(String id);
    Comment update(Comment comment);
    void delete(String id);
}
