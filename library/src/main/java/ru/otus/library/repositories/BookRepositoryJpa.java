package ru.otus.library.repositories;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Book;

import javax.persistence.*;
import java.util.*;

@Repository
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Book save(Book book) {
        if (book.getId() > 0) {
            return em.merge(book);
        } else {
            em.persist(book);
            return book;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getById(long id) {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b " +
                        "join fetch b.authors " +
                        "join fetch b.genres " +
                        "where b.id=:id",
                Book.class);
        query.setParameter("id", id);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery(
                "select distinct b " +
                        "from Book b " +
                        "left join fetch b.authors " +
                        "left join fetch b.genres",
                Book.class);
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookByIdWithComments(long id) {
        TypedQuery<Book> query = em.createQuery(
                "select distinct b " +
                        "from Book b " +
                        "left join fetch b.comments " +
                        "left join fetch b.authors " +
                        "left join fetch b.genres " +
                        "where b.id=:bookId",
                Book.class);
        query.setParameter("bookId", id);
        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getByAuthorId(long authorId) {
        TypedQuery<Book> query = em.createQuery(
                "select distinct b " +
                        "from Book b " +
                        "join fetch b.authors " +
                        "join fetch b.genres " +
                        "where (select a from Author a where a.id=:authorId) member of b.authors",
                Book.class);
        query.setParameter("authorId", authorId);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getByGenreId(long genreId) {
        TypedQuery<Book> query = em.createQuery(
                "select distinct b " +
                        "from Book b " +
                        "join fetch b.authors " +
                        "join fetch b.genres g " +
                        "where (select g1 from Genre g1 where g1.id=:genreId) member of b.genres",
                Book.class);
        query.setParameter("genreId", genreId);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


}