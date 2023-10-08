package dev.aloyisius.Bookcatalogservice.Repository;

import dev.aloyisius.Bookcatalogservice.Domain.Books;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Books,Long> {

    Optional<Books> findBooksByIsbn(String isbn);
}
