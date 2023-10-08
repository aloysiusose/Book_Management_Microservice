package dev.aloyisius.Bookcatalogservice;

import dev.aloyisius.Bookcatalogservice.Domain.Author;
import dev.aloyisius.Bookcatalogservice.Domain.Books;
import dev.aloyisius.Bookcatalogservice.Repository.AuthorRepository;
import dev.aloyisius.Bookcatalogservice.Services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BookCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BookService bookService, AuthorRepository authorRepository){
		return args -> {
			Author author1 = new Author();
			Author author2 = new Author();
			author1.setFirstName("Francis");
			author1.setLastName("Asisi");
			author1.setEmail("francis.asisi@bcat.com");
			authorRepository.save(author1);
			author1.setFirstName("Padre");
			author1.setLastName("Pio");
			author1.setEmail("padre.pio@bcat.com");

			authorRepository.save(author2);
			Books books = new Books();
			books.setTitle("trust the process");
			books.setIsbn("12345678");
			books.setEdition("1st Edition");
			books.setPrice(1.00);
			books.setStockLevel(2);
			books.setAuthors(List.of(author1));

			bookService.addBooks(books);

		};
	}

}
