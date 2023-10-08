package dev.aloyisius.Bookcatalogservice.Repository;

import dev.aloyisius.Bookcatalogservice.Domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
