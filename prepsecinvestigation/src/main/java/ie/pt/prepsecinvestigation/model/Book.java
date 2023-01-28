package ie.pt.prepsecinvestigation.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Book {

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "number_of_page")
    private String numberOfPage;

    @Column(name = "author")
    private String author;

}