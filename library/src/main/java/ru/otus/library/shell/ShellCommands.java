package ru.otus.library.shell;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.dto.AuthorDto;
import ru.otus.library.models.*;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.CommentService;
import ru.otus.library.service.GenreService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {

    @NonNull
    AuthorService authorService;

    @NonNull
    BookService bookService;

    @NonNull
    GenreService genreService;

    @NonNull
    CommentService commentService;

    //BOOK COMMANDS
    @ShellMethod(value = "get-book", key = {"gb"})
    public String getBook(@ShellOption long id) {
        Optional<Book> bookOpt = bookService.getBookById(id);
        return bookOpt.toString();//.get().orElse().toString();
    }

    @ShellMethod(value = "get-book-author", key = {"gba"})
    public String getBookByAuthor(@ShellOption long aid) {
        List<Book> books = bookService.getBooksByAuthorId(aid);
        return books.toString();
    }

    @ShellMethod(value = "get-books", key = {"gbs"})
    public String getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return books.toString();
    }

    @ShellMethod(value = "get-book-genre", key = {"gbg"})
    public String getBookByGenre(@ShellOption long gid) {
        List<Book> books = bookService.getBooksByGenreId(gid);
        return books.toString();
    }

    @ShellMethod(value = "get-book-with-comments", key = {"gbc"})
    public String getBookWithComments(@ShellOption long gid) {
        Optional<Book> optionalBook = bookService.getBookWithComments(gid);
        return optionalBook.get().toStringWithComments();
    }

    @ShellMethod(value = "create-book", key = {"cb"})
    public void createBook(@ShellOption String title,
                           @ShellOption String authorsStr,
                           @ShellOption String genresStr) {
        Set<Long> authorIdList = parseIds(authorsStr);
        Set<Long> genresIdList = parseIds(genresStr);

        try {
            var authors = authorService.findByIds(authorIdList);
            var genres = genreService.findByIds(genresIdList);
            Book book = new Book(0, authors, genres, title, null);
            bookService.createBook(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "update_book", key = {"ub"})
    public String updateBook(@ShellOption long bookId,
                             @ShellOption String title,
                             @ShellOption String authorsStr,
                             @ShellOption String genresStr) {
        var bookOptional = bookService.getBookById(bookId);
        var book = bookOptional.get();
        if (book == null)
            return "book not found";

        Set<Long> authorIdList = parseIds(authorsStr);
        Set<Long> genresIdList = parseIds(genresStr);
        var authors = authorService.findByIds(authorIdList);
        var genres = genreService.findByIds(genresIdList);

        if (!authors.isEmpty())
            book.setAuthors(authors);

        if (!genres.isEmpty())
            book.setGenres(genres);

        if (!title.equals("_"))
            book.setTitle(title);

        return bookService.updateBook(book).toString();
    }

    @ShellMethod(value = "delete_book", key = {"db"})
    public void deleteBook(@ShellOption long bookId) {
        bookService.deleteBookById(bookId);
    }

    // AUTHOR COMMANDS
    @ShellMethod(value = "create-author", key = {"ca"})
    public void createAuthor(@ShellOption String firstName,
                             @ShellOption String middleName,
                             @ShellOption String lastName) {
        String mn = (middleName.equals("_")) ? "" : middleName;
        AuthorDto author = new AuthorDto(firstName, mn, lastName);
        authorService.createAuthor(author);
    }

    @ShellMethod(value = "get-authors", key = {"gas"})
    public String getAllAuthors() {
        return authorService.getAuthors().toString();
    }

    // GENRES COMMANDS
    @ShellMethod(value = "create-genre", key = {"cg"})
    public void createAuthor(@ShellOption String title) {
        Genre genre = new Genre(0, title);
        genreService.create(genre);
    }

    @ShellMethod(value = "get-genres", key = {"ggs"})
    public String getAllGenres() {
        return genreService.getAllGenres().toString();
    }

    // COMMENT COMMAND
    @ShellMethod(value = "create-comment", key = {"cc"})
    public String createComment(@ShellOption long bookId,
                                @ShellOption String text) {
        Comment comment = new Comment(0, bookId, text);
        return commentService.save(comment).toString();
    }

    @ShellMethod(value = "update-comment", key = {"uc"})
    public String updateComment(@ShellOption long id,
                                @ShellOption String text) {
        Comment comment = commentService.getById(id);
        comment.setText(text);
        return commentService.save(comment).toString();
    }

    @ShellMethod(value = "get-comment", key = {"gc"})
    public String getComment(@ShellOption long id) {
        return commentService.getById(id).toString();
    }

    @ShellMethod(value = "delete-comment", key = {"dc"})
    public void deleteComment(@ShellOption long id) {
        commentService.delete(id);
    }

    //парсим id-шники разделенные символом подчеркивания
    private Set<Long> parseIds(String inputString) {
        return Arrays.stream(inputString.split("_"))
                .filter(a -> !a.equals(""))
                .map(a -> Long.parseLong(a))
                .collect(Collectors.toSet());
    }
}
