package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Comment;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.repositories.CommentRepository;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository repository;

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public Comment getById(long id) {
        return repository.getById(id);
    }

    @Override
    public Set<Comment> getByBookId(long bookId) {
        return bookRepository.getBookByIdWithComments(bookId).get().getComments();
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        return repository.save(comment);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.delete(id);
    }
}
