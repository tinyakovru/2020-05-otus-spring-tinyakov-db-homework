package ru.otus.library.service;

import ru.otus.library.models.Comment;

import java.util.List;

public interface CommentService {
    public Comment save(Comment comment);
    public Comment getById(long id);
    public List<Comment> getByBookId(long bookId);
    public Comment update(Comment comment);
    public void delete(long id);
}
