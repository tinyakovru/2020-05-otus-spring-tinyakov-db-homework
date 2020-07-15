package ru.otus.library.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {
    private NamedParameterJdbcOperations jdbc;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void create(String title) {
        Map<String, String> params = new HashMap<>() {{
            put("title", title);
        }};
        jdbc.update("insert into genre (title) values (:title)", params);
    }

    @Override
    public List<Genre> getAll() {
        return jdbc.query("select * from genre",
                (resultSet, i) -> {
                    return new Genre(resultSet.getLong("id"),
                            resultSet.getString("title"));
                });
    }
}