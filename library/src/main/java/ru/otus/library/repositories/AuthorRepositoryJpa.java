//package ru.otus.library.repositories;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ru.otus.library.models.Author;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.TypedQuery;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//@Repository
//public class AuthorRepositoryJpa implements AuthorRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    @Override
//    public Author save(Author author) {
//        if (author.getId() > 0) {
//            return em.merge(author);
//        } else {
//            em.persist(author);
//            return author;
//        }
//    }
//
//    @Override
//    public Optional<Author> findById(long id) {
//        return Optional.ofNullable(em.find(Author.class, id));
//    }
//
//    @Override
//    public List<Author> findAll() {
//        return em.createQuery("select a from Author a",Author.class).getResultList();
//    }
//
//    @Override
//    public Set<Author> findByIds(Set<Long> ids) {
//        TypedQuery<Author> query = em.createQuery("select a from Author a where a.id in :ids", Author.class);
//        query.setParameter("ids", ids);
//        return new HashSet<Author>(query.getResultList());
//    }
//}
