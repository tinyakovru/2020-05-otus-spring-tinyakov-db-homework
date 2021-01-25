package ru.otus.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.repositories.CommentRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
//    private final BookRepository bookRepository;


    @Override
    @Transactional
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment create(String bookId, String text) {
//        Book book = bookRepository.findById(bookId).orElseThrow();
        return commentRepository.save(new Comment(bookId,text));
    }

    @Override
    public Optional<Comment> getById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    @Transactional
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void delete(String id) {
        commentRepository.deleteById(id);
    }
}
