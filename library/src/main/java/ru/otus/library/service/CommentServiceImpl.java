package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.library.models.Comment;
import ru.otus.library.repositories.CommentRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Comment getById(long id) {
        return repository.getById(id);
    }

    @Override
    public List<Comment> getByBookId(long bookId) {
        return repository.getByBookId(bookId);
    }

    @Override
    public Comment update(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public void delete(long id) {
        repository.delete(id);
    }
}
