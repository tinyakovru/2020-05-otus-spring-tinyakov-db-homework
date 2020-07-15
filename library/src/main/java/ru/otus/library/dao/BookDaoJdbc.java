package ru.otus.library.dao;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.library.domain.Author;
import ru.otus.library.domain.Book;
import ru.otus.library.domain.BookDto;
import ru.otus.library.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookDaoJdbc implements BookDao {
    protected NamedParameterJdbcOperations jdbc;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Book getById(long id) {
        Map<String, Long> params = new HashMap<String, Long>() {{
            put("id", id);
        }};
        String query = getQuery("b.id = :id");
        List<BookResult> bookResults = jdbc.query(query, params, new BookResultMapper());
        List<Book> books = bookResult2Book(bookResults);
        return books.get(0);
    }

    @Override
    public List<Book> getByAuthor(long authorId) {
        Map<String, Long> params = new HashMap<String, Long>() {{
            put("id", authorId);
        }};
        String query = getQuery("a.id = :id");
        List<BookResult> bookResults = jdbc.query(query, params, new BookResultMapper());
        List<Book> books = bookResult2Book(bookResults);
        return books;
    }

    @Override
    public List<Book> getByGenre(long genreId) {
        Map<String, Long> params = new HashMap<String, Long>() {{
            put("id", genreId);
        }};
        String query = getQuery("g.id = :id");
        List<BookResult> bookResults = jdbc.query(query, params, new BookResultMapper());
        List<Book> books = bookResult2Book(bookResults);
        return books;
    }

    @Override
    public List<Book> getAll() {
        Map<String, Long> params = new HashMap<String, Long>();
        String query = getQuery("1");
        List<BookResult> bookResults = jdbc.query(query, params, new BookResultMapper());
        List<Book> books = bookResult2Book(bookResults);
        return books;
    }

    @Override
    public long createBook(BookDto bookDto) {
        long bookId = insertBookTitle(bookDto.getTitle());
        if (bookDto.getAuthorsIds().size() > 0) {
            System.out.println(bookDto.getAuthorsIds());
            addBookRelations(BookRelationType.AUTHOR, bookId, bookDto.getAuthorsIds());
            //            addBookAuthor(bookId, bookDto.getAuthorsIds());
        }
        if (bookDto.getGenresIds().size() > 0) {
            addBookRelations(BookRelationType.GENRE, bookId, bookDto.getGenresIds());
        }
        return bookId;
    }

    private void removeBookRelations(BookRelationType relationType, long bookId) {
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("book_id", bookId);
        jdbc.update("delete from " + relationType.getRelationTableName() + " where book_id=:book_id", mapSqlParameterSource);
    }

    private void addBookRelations(BookRelationType relationType, long bookId, Set<Long> relationIds) {
        List<SqlParameterSource> batchParams = new ArrayList<SqlParameterSource>();
        for (long aid : relationIds) {
            MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("book_id", bookId);
            mapSqlParameterSource.addValue(relationType.getColumnName(), aid);
            batchParams.add(mapSqlParameterSource);
        }
        jdbc.batchUpdate("insert into " + relationType.getRelationTableName() + " (book_id, " + relationType.getColumnName() + ") values (:book_id, :" + relationType.getColumnName() + ")",
                batchParams.toArray(new MapSqlParameterSource[relationIds.size()]));
    }

    private long insertBookTitle(String title) {
        SqlParameterSource paramsBook = new MapSqlParameterSource("title", title);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into book (title) values (:title)", paramsBook, keyHolder);
        long id = (long) keyHolder.getKey();
        System.out.printf("id = %d", id);
        return id;
    }

    @Override
    public void updateBook(BookDto bookDto) {
        if (!bookDto.getTitle().equals("_")) {
            Map<String,Object> bookTitleParam = new HashMap<>(){{
               put("id",bookDto.getId());
               put("title",bookDto.getTitle());
            }};
            jdbc.update("update book set title=:title where id=:id", bookTitleParam);
        }
        if (!bookDto.getGenresIds().isEmpty()) {
            removeBookRelations(BookRelationType.GENRE, bookDto.getId());
            addBookRelations(BookRelationType.GENRE, bookDto.getId(), bookDto.getGenresIds());
        }
        if (!bookDto.getAuthorsIds().isEmpty()) {
            removeBookRelations(BookRelationType.AUTHOR, bookDto.getId());
            addBookRelations(BookRelationType.AUTHOR,bookDto.getId(),bookDto.getAuthorsIds());
        }
    }

    @Override
    public void deleteById(long bookId) {
        removeBookRelations(BookRelationType.GENRE,bookId);
        removeBookRelations(BookRelationType.AUTHOR,bookId);
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",bookId);
        jdbc.update("delete from book where id=:id", params);
    }

    private String getQuery(String where) {
        return "select " +
                "  b.id as book_id,  " +
                "  b.title as book_title,  " +
                "  a.id as author_id, " +
                "  a.first_name as first_name,  " +
                "  a.middle_name as middle_name, " +
                "  a.last_name as last_name, " +
                "  g.id as genre_id, " +
                "  g.title as genre_title  " +
                "from   " +
                "  book as b " +
                "  left join book_author as ba on b.id=ba.book_id " +
                "  left join book_genre as bg on b.id=bg.book_id " +
                "  left join author as a on a.id=ba.author_id  " +
                "  left join genre as g on g.id = bg.genre_id  " +
                "where " + where;
    }

    private List<Book> bookResult2Book(List<BookResult> results) {
        Map<Long, Book> books = new HashMap<>();
        for (BookResult row : results) {
            long id = row.getBookId();
            Book book;
            if (books.containsKey(id)) {
                book = books.get(id);
            } else {
                book = new Book(id, null, null, row.getBookTitle());
                books.put(id, book);
            }
            if (row.getAuthorId() > 0) {
                Author author = new Author(row.getAuthorId(), row.getFirstName(), row.getMiddleName(), row.getLastName());
                book.getAuthors().add(author);
            }
            if (row.getGenreId() > 0) {
                Genre genre = new Genre(row.getGenreId(), row.getGenreTitle());
                book.getGenres().add(genre);
            }
        }
        return new ArrayList<Book>(books.values());
    }

    private static class BookResultMapper implements RowMapper<BookResult> {
        @Override
        public BookResult mapRow(ResultSet rs, int i) throws SQLException {
            return new BookResult(rs.getLong("book_id"),
                    rs.getString("book_title"),
                    rs.getLong("author_id"),
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getString("last_name"),
                    rs.getLong("genre_id"),
                    rs.getString("genre_title"));
        }
    }

    @Data
    @RequiredArgsConstructor
    private static class BookResult {
        private final long bookId;
        private final String bookTitle;
        private final long authorId;
        private final String firstName;
        private final String middleName;
        private final String lastName;
        private final long genreId;
        private final String genreTitle;
    }

    private enum BookRelationType {
        AUTHOR("author_id", "book_author"),
        GENRE("genre_id", "book_genre");

        private final String columnName;
        private final String relationTableName;

        public String getColumnName() {
            return columnName;
        }

        public String getRelationTableName() {
            return relationTableName;
        }

        BookRelationType(String column_name, String relation_table_name) {
            this.columnName = column_name;
            this.relationTableName = relation_table_name;
        }
    }
}