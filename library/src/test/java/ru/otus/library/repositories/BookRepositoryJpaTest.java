package ru.otus.library.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DisplayName("тестируем BookRepositoryJpa")
@Import(BookRepositoryJpa.class)
class BookRepositoryJpaTest {

    private static final String TITLE_BOOK_8 = "10 негритят";
    private static final String TITLE_BOOK_13 = "12 стульев";
    private static final long BOOK_ID_8 = 8;
    private static final long BOOK_ID_13 = 13;
    private static final long AUTHOR_ID = 6;
    private static final long BOOKID_BY_AUTHOR_1 = 13;
    private static final long BOOKID_BY_AUTHOR_2 = 14;
    private static final long DELETE_BOOK_ID = 2;
    private static final long DELETED_BOOK_COMMENT_ID = 2;

    @Autowired
    BookRepositoryJpa repositoryJpa;

    @Autowired
    TestEntityManager em;

    @Test
    @DisplayName("должен доставать книгу по id с авторами и жанрами, но без комментариев")
    void ShouldGetById() {
        Optional<Book> optionalActualBook = repositoryJpa.getById(BOOK_ID_13);
        Book expectedBook = em.find(Book.class, BOOK_ID_13);
        assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook)
                .hasFieldOrPropertyWithValue("title", TITLE_BOOK_13);
    }

    @Test
    @DisplayName("должен доставать книгу по id с авторами и жанрами и с комментариями")
    void shouldGetBookByIdWithComments() {
        Optional<Book> optionalActualBook = repositoryJpa.getBookByIdWithComments(BOOK_ID_8);
        Book expectedBook = em.find(Book.class, BOOK_ID_8);
        assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook)
                .hasFieldOrPropertyWithValue("title", TITLE_BOOK_8);
        Set<Comment> comments = optionalActualBook.get().getComments();
        assertThat(comments).isNotEmpty().hasSize(3);
    }

    @Test
    @DisplayName("должен доставать книгу по id автора")
    void shouldGetBookByAuthorId() {
        List<Book> books = repositoryJpa.getByAuthorId(AUTHOR_ID);
        Book book1 = em.find(Book.class, BOOKID_BY_AUTHOR_1);
        Book book2 = em.find(Book.class, BOOKID_BY_AUTHOR_2);
        assertThat(books)
                .isNotEmpty()
                .hasSize(2);
        assertThat(books.get(0)).isEqualToComparingFieldByField(book1);
        assertThat(books.get(1)).isEqualToComparingFieldByField(book2);
    }

    @Test
    @DisplayName("должен удалять книгу по id вмеси=те со связанными комментариями")
    void shouldDeleteById() {
        Book book = em.find(Book.class, DELETE_BOOK_ID);
        assertThat(book).isNotNull();

        repositoryJpa.deleteById(DELETE_BOOK_ID);
        em.flush();
        em.clear();

        Comment comment = em.find(Comment.class, DELETED_BOOK_COMMENT_ID);
        assertThat(comment).isNull();
        book = em.find(Book.class, DELETE_BOOK_ID);
        assertThat(book).isNull();
    }
}