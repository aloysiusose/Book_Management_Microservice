package dev.aloyisius.Bookcatalogservice.Domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.util.List;
@Entity
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToMany(mappedBy = "booksList")
    @JsonManagedReference
    private List<Author> authors;
    private String title;
    @NonNull
    private String isbn;
    private String edition;
    private double price;
    private long stockLevel;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(price<=0){
            throw new RuntimeException("Price must be valid");
        }
        this.price = price;
    }

    public long getStockLevel() {
        return stockLevel;
    }

    public void setStockLevel(long stockLevel) {
        this.stockLevel = stockLevel;
    }
}
