package ru.otus.library.shell;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.library.domain.*;
import ru.otus.library.service.AuthorService;
import ru.otus.library.service.BookService;
import ru.otus.library.service.GenreService;

import java.util.Arrays;
import java.util.List;
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

    //BOOK COMMANDS
    @ShellMethod(value = "get-book", key = {"gb"})
    public String getBook(@ShellOption long id) {
        Book book = bookService.getBookById(id);
        return book.toString();
    }

    @ShellMethod(value = "get-book-author", key = {"gba"})
    public String getBookByAuthor(@ShellOption long aid) {
        List<Book> books = bookService.getBooksByAuthor(aid);
        return books.toString();
    }

    @ShellMethod(value = "get-books", key = {"gbs"})
    public String getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return books.toString();
    }

    @ShellMethod(value = "get-book-genre", key = {"gbg"})
    public String getBookByGenre(@ShellOption long gid) {
        List<Book> books = bookService.getBooksByGenre(gid);
        return books.toString();
    }

    @ShellMethod(value = "create-book", key = {"cb"})
    public void createBook(@ShellOption String title,
                           @ShellOption String authors,
                           @ShellOption String genres) {
        Set<Long> authorIdList = parseIds(authors);
        Set<Long> genresIdList = parseIds(genres);
        BookDto bookDto = new BookDto(0, title, authorIdList, genresIdList);
        try {
            bookService.createBook(bookDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ShellMethod(value = "update_book", key = {"ub"})
    public void updateBook(@ShellOption long bookId,
                           @ShellOption String title,
                           @ShellOption String authors,
                           @ShellOption String genres) {
        Set<Long> authorIdList = parseIds(authors);
        Set<Long> genresIdList = parseIds(genres);
        bookService.updateBook(bookId, title, authorIdList, genresIdList);
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
        String mn = (middleName == "_") ? "" : middleName;
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
        GenreDto author = new GenreDto(title);
        genreService.create(title);
    }

    @ShellMethod(value = "get-genres", key = {"ggs"})
    public String getAllGenres() {
        return genreService.getGenres().toString();
    }


    //парсим id-шники разделенные символом подчеркивания
    private Set<Long> parseIds(String inputString) {
        return Arrays.stream(inputString.split("_"))
                .filter(a -> !a.equals(""))
                .map(a -> Long.parseLong(a))
                .collect(Collectors.toSet());
    }
}
