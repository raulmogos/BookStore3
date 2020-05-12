package ro.bookstore3.web.converter;

import org.springframework.stereotype.Component;
import ro.bookstore3.web.dto.BookDto;
import ro.bookstore3.models.Book;

@Component
public class BookConverter extends BaseConverter<Book, BookDto> {
    @Override
    public Book convertDtoToModel(BookDto dto) {
        Book book = new Book(dto.getTitle(), dto.getAuthor(), dto.getPrice());
        book.setId(dto.getId());
        return book;
    }

    @Override
    public BookDto convertModelToDto(Book book) {
        BookDto dto = new BookDto(book.getTitle(), book.getAuthor(), book.getPrice());
        dto.setId(book.getId());
        return dto;
    }
}
