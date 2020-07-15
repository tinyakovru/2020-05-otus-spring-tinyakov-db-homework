package ru.otus.library.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
public class Book {
    @NonNull
    private final long id;
    private Set<Author> authors; //не ставлю final, чтобы иметь возможность добавлять авторов/жанры при парсинге строк из БД

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", \nauthors=" + authors +
                ", \ngenres=" + genres +

                '}'+"\n";
    }

    private Set<Genre> genres;  //тоже самое
    private final String title;

    public Book(@NonNull long id, Set<Author> authors, Set<Genre> genres, String title) {
        this.id = id;
        this.authors = authors == null ? new HashSet<Author>() : authors;
        this.genres = genres == null ? new HashSet<>() : genres;
        this.title = title;
    }
}
