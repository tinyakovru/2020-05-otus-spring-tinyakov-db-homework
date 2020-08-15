package ru.otus.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ru.otus.library.models.Genre;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface GenreRepository extends JpaRepository<Genre,Long> {
//    Genre save(Genre genre);
//    Optional<Genre> findById(long id);
//    List<Genre> findAll();
    Set<Genre> findByIdIn(Set<Long> ids);
//    Set<Genre> findByIds(Set<Long> ids);

}
