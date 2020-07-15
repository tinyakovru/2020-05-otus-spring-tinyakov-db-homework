package ru.otus.library.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.AuthorDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private NamedParameterJdbcOperations jdbc;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void insertAuthor(AuthorDto authorDto) {
        Map<String, String> params = new HashMap<>() {{
            put("first_name", authorDto.getFirstName());
            put("middle_name", authorDto.getMiddleName());
            put("last_name", authorDto.getLastName());
        }};

        jdbc.update("insert into author (first_name,middle_name,last_name) " +
                "values (:first_name, :middle_name, :last_name)", params);
    }

    @Override
    public List<Author> getAll() {
        return jdbc.query("select * from author",
                (resultSet, i) -> {
                    return new Author(resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("middle_name"),
                            resultSet.getString("last_name"));
                });
    }


}
