package ru.otus.library.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.library.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Genre save(Genre genre) {
        if (genre.getId() > 0) {
            return em.merge(genre);
        } else {
            em.persist(genre);
            return genre;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class,id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> findAll() {
        return em.createQuery("select g from Genre g", Genre.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Genre> findByIds(Set<Long> ids) {
        TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.id in :ids",Genre.class);
        query.setParameter("ids",ids);
        return new HashSet<Genre>(query.getResultList());
    }
}