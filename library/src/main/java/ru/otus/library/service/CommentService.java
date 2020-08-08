package ru.otus.library.service;

import ru.otus.library.models.Comment;

import java.util.Set;

public interface CommentService {
    Comment save(Comment comment);
    Comment getById(long id);
    Comment update(Comment comment);
    void delete(long id);
}
