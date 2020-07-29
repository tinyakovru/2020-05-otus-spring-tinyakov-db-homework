package ru.otus.library.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Comment save(Comment comment) {
        if (comment.getId() > 0) {
            return em.merge(comment);
        } else {
            em.persist(comment);
            return comment;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Comment getById(long id) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.id=:id", Comment.class);
        query.setParameter("id",id);
        return query.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getByBookId(long bookId) {
        TypedQuery<Comment> query = em.createQuery("select c from Comment c where c.bookId=:bookId", Comment.class);
        query.setParameter("bookId",bookId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void delete(long id) {
        Query query = em.createQuery("delete from Comment c where c.id=:id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

}
