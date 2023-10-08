package dev.aloyisius.Bookcatalogservice.Domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;
@Entity
public class Author {
    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "authors_list"), inverseJoinColumns = @JoinColumn(name = "authors"))
    @JsonBackReference
    private List<Books> booksList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Books> getBooksList() {
        return booksList;
    }

    public void setBooksList(List<Books> booksList) {
        this.booksList = booksList;
    }
}
