package ro.bookstore3.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
public class BooksDto {
    private Set<BookDto> books;

    public BooksDto(Set<BookDto> convertModelsToDtos) {
        books = convertModelsToDtos;
    }

    public Set<BookDto> getBooks() {
        return books;
    }
}
