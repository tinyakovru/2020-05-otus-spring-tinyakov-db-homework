package ru.otus.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "book")
public class Book {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Field
    private List<Author> authors;

    @Field
    private Set<Genre> genres;

    @Field
    private String title;


    //Set<Comment> comments;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", \nauthors=" + authors +
                ", \ngenres=" + genres +
                '}' + "\n";
    }

    /*public String toStringWithComments() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", \nauthors=" + authors +
                ", \ngenres=" + genres +
                ", \ncomments=" + comments +
                '}' + "\n";
    }*/
}
