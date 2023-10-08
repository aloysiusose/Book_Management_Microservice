package dev.aloyisius.Bookcatalogservice.Domain;

public record BookDetailsDTO(String bookTile,
                             java.util.List<Author> author,
                             String isbn,
                             double price,
                             String edition) {
}
