package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.library.models.Comment;

import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long> {
//    Comment save(Comment comment);
//
//    Comment getById(long id);
//
//    void delete(long id);

//    Set<Comment> findAll();
}
