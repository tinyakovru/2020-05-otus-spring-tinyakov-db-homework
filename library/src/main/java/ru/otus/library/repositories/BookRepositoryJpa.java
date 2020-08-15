//package ru.otus.library.repositories;
//
//import org.hibernate.jpa.QueryHints;
//import org.springframework.stereotype.Repository;
//import ru.otus.library.models.Book;
//
//import javax.persistence.*;
//import java.util.*;
//
//@Repository
//public class BookRepositoryJpa implements BookRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public Book save(Book book) {
//        if (book.getId() > 0) {
//            return em.merge(book);
//        } else {
//            em.persist(book);
//            return book;
//        }
//    }
//
//    @Override
//    public Optional<Book> getById(long id) {
//        return Optional.ofNullable(em.find(Book.class,id));
//    }
//
//    @Override
//    public List<Book> getAllBooks() {
//        TypedQuery<Book> query = em.createQuery(
//                "select distinct b " +
//                        "from Book b " +
//                        "left join fetch b.authors " +
//                        "left join fetch b.genres",
//                Book.class);
//        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
//        return query.getResultList();
//    }
//
//    @Override
//    public List<Book> getByAuthorId(long authorId) {
//        TypedQuery<Book> query = em.createQuery(
//                "select distinct b " +
//                        "from Book b " +
//                        "join fetch b.authors " +
//                        "join fetch b.genres " +
//                        "where (select a from Author a where a.id=:authorId) member of b.authors",
//                Book.class);
//        query.setParameter("authorId", authorId);
//        return query.getResultList();
//    }
//
//    @Override
//    public List<Book> getByGenreId(long genreId) {
//        TypedQuery<Book> query = em.createQuery(
//                "select distinct b " +
//                        "from Book b " +
//                        "join fetch b.authors " +
//                        "join fetch b.genres g " +
//                        "where (select g from Genre g where g.id=:genreId) member of b.genres",
//                Book.class);
//        query.setParameter("genreId", genreId);
//        return query.getResultList();
//    }
//
//    @Override
//    public void deleteById(long id) {
//        Book book = em.find(Book.class,id);
//        em.remove(book);
//    }
//}