package dev.aloyisius.Bookcatalogservice.Services;

import dev.aloyisius.Bookcatalogservice.Domain.BookDetailsDTO;
import dev.aloyisius.Bookcatalogservice.Domain.Books;
import dev.aloyisius.Bookcatalogservice.Repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBooks(Books book) {
        if (isBookPresent(book)) {
            Books books = bookRepository.findBooksByIsbn(book.getIsbn()).get();
            books.setStockLevel(book.getStockLevel() + book.getStockLevel());
            bookRepository.save(books);
        }
        bookRepository.save(book);
        /*
        add book if not exits, if it exists increase the stock level and call the inventory service
         */
    }

    private boolean isBookPresent(Books book) {
        return bookRepository.findBooksByIsbn(book.getIsbn()).isPresent();
    }

    public void updateBooks(Books books) {
        if(!isBookPresent(books)){
            throw new RuntimeException("""
                    """);
        }
        Books book = bookRepository.findBooksByIsbn(books.getIsbn()).get();
        book.setAuthors(books.getAuthors());
        book.setTitle(books.getTitle());
        bookRepository.save(book);

        /*
        check if the book exist, update and call the inventory service if not throw an error
         */
    }

    public BookDetailsDTO bookDetails(String isbn) {
        return bookRepository.findBooksByIsbn(isbn).map(
                BookService::bookMapper).orElseThrow();

    }

    private static BookDetailsDTO bookMapper(Books books){
        return new BookDetailsDTO(
                books.getTitle(),
                books.getAuthors(), books.getIsbn(), books.getPrice(), books.getEdition()
        );
    }
}
