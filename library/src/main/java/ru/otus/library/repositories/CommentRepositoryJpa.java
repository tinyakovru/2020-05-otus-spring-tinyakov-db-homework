package ru.otus.library.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.library.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() > 0) {
            return em.merge(comment);
        } else {
            em.persist(comment);
            return comment;
        }
    }

    @Override
    public Comment getById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public void delete(long id) {
        em.remove(em.find(Comment.class, id));
    }

}
