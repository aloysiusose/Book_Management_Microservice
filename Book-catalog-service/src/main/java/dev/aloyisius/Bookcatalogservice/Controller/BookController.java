package dev.aloyisius.Bookcatalogservice.Controller;

import dev.aloyisius.Bookcatalogservice.Domain.BookDetailsDTO;
import dev.aloyisius.Bookcatalogservice.Domain.Books;
import dev.aloyisius.Bookcatalogservice.Services.BookService;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/api/v1")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello from the server";
    }

    @PostMapping("/books/add-book")
    public void addBooks(Books book){
        bookService.addBooks(book);

    }

    @PutMapping("/books/update-books")
    public void updateBook(Books books){
        bookService.updateBooks(books);
    }
    @GetMapping("/search")
    public Books searchForBook(){
        return null;
    }

    @DeleteMapping("/books/delete-book/")
    public void deleteBook(){
        //admin only
    }
    @GetMapping("/book-details/{isbn}")
    public BookDetailsDTO bookDetails(@PathVariable String isbn){
        return bookService.bookDetails(isbn);
    }

    /*
    Create a New Book: This endpoint allows administrators to add new books to the catalog. It should accept book details like title, author, genre, price, and initial stock level.

Update Book Information: Administrators can update existing book details using this endpoint, such as changing the price or genre.

Delete a Book: This endpoint allows administrators to remove books from the catalog.

Retrieve Book Details: Users (or other services) can retrieve book information, including availability, by providing the book's ID or ISBN.
cd
Search for Books: Implement a search endpoint that allows users to search for books based on various criteria, such as title, author, or genre.
     */
}
