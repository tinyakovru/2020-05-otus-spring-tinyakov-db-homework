package ru.otus.library.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import ru.otus.library.models.Author;
import ru.otus.library.models.Book;
import ru.otus.library.models.Comment;
import ru.otus.library.repositories.AuthorRepository;
import ru.otus.library.repositories.BookRepository;
import ru.otus.library.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
//import static org.jline.utils.AttributedStringBuilder.append;

@ChangeLog(order = "001")
public class DatabaseChangelog {

    @ChangeSet(order = "000", id = "dropDB", author = "tinyakov", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "addAuthors", author = "tinyakov", runAlways = true)
    public void inserAuthors(MongoTemplate template) {
        template.save(new Author(null,"Евгений","Петрович","Петров"));
        template.save(new Author(null,"Илья","Арнольдович","Ильф"));
        template.save(new Author(null,"Александр","Сергеевич","Пушкин"));
//        System.out.println("ChangeSet 000");
//        MongoCollection<Document> myCollection = db.getCollection("author");
//        var doc = new Document().append("last_name", "Петров")
//                .append("first_name", "Евгений")
//                .append("middle_name", "Петрович");
//        myCollection.insertOne(doc);
//        doc = new Document().append("last_name", "Ильф")
//                .append("first_name", "Илья")
//                .append("middle_name", "Арнольдович");
//        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "002", id = "addGenres", author = "tinyakov", runAlways = true)
    public void inserGenres(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genre");
        var doc = new Document().append("title", "проза");
        myCollection.insertOne(doc);
        doc = new Document().append("title", "поэзия");
        myCollection.insertOne(doc);
        doc = new Document().append("title", "комедия");
        myCollection.insertOne(doc);
        doc = new Document().append("title", "детектив");
        myCollection.insertOne(doc);
    }

    @ChangeSet(order = "003", id = "addBooks", author = "tinyakov", runAlways = true)
    public void inserBooks(MongoDatabase db) {
        MongoCollection<Document> aCollection = db.getCollection("author");
        FindIterable<Document> authors = aCollection.find(in("last_name", "Ильф", "Петров"));

        MongoCollection<Document> gCollection = db.getCollection("genre");
        FindIterable<Document> genres = gCollection.find(in("title", "комедия", "проза"));

        MongoCollection<Document> myCollection = db.getCollection("book");
        var book1 = new Document()
                .append("title", "12 стульев")
                .append("authors", authors)
                .append("genres", genres);
        var book2 = new Document()
                .append("title", "Золотой теленок")
                .append("authors", authors)
                .append("genres", genres);
        var book3 = new Document()
                .append("title", "Евгений Онегин")
                .append("authors", authors)
                .append("genres", genres);
        var books = new ArrayList<Document>();
        books.add(book1);
        books.add(book2);
        myCollection.insertMany(books);
    }

    @ChangeSet(order = "004", id = "addBook2", author = "tinyakov", runAlways = true)
    public void inserBook2(MongoDatabase db) {
        MongoCollection<Document> aCollection = db.getCollection("author");
        FindIterable<Document> authors = aCollection.find(in("last_name", "Пушкин"));

        MongoCollection<Document> gCollection = db.getCollection("genre");
        FindIterable<Document> genres = gCollection.find(in("title", "поэзия"));

        MongoCollection<Document> myCollection = db.getCollection("book");

        var book = new Document()
                .append("title", "Евгений Онегин")
                .append("authors", authors)
                .append("genres", genres);
        myCollection.insertOne(book);
    }

    @ChangeSet(order = "005", id = "addComment", author = "tinyakov", runAlways = true)
    public void addComment(MongoDatabase db, MongoTemplate template) {

        Document book = db.getCollection("book").find().first();
        var bookId = book.get("_id").toString();
//        MongoCollection<Document> myCollection = db.getCollection("comment");

        template.save(new Comment(bookId,"great book!"));
        template.save(new Comment(bookId,"hohoho"));
    }
}
