package ie.pt.prepsecinvestigation.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "roll_number")
    private String rollNumber;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "student_book", joinColumns = @JoinColumn(name = "student_id"))
    private List<Book> bookList = new ArrayList<Book>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "student_phone_number", joinColumns = @JoinColumn(name = "student_id"))
    private Set<String> phoneNumbers = new HashSet<String>();


}