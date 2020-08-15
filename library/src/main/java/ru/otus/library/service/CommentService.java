package ru.otus.library.service;

import ru.otus.library.models.Comment;

import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);
    Optional<Comment> getById(long id);
    Comment update(Comment comment);
    void delete(long id);
}
