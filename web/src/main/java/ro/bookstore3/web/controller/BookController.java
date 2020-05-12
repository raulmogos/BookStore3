package ro.bookstore3.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.bookstore3.models.Book;
import ro.bookstore3.services.book.BookService;
import ro.bookstore3.web.converter.BookConverter;
import ro.bookstore3.web.dto.BookDto;
import ro.bookstore3.web.dto.BooksDto;

import java.util.stream.Collectors;

@RestController
public class BookController {
    public static final Logger log= LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookConverter bookConverter;


    @RequestMapping(value = "/books", method = RequestMethod.GET)
    BooksDto getBooks() {
        //todo: log
        return new BooksDto(bookConverter
                .convertModelsToDtos(bookService.getAllBooks()));

    }

    @RequestMapping(value = "/books", method = RequestMethod.POST)
    BookDto saveBook(@RequestBody BookDto bookDto) {
        //todo log
        return bookConverter.convertModelToDto(bookService.addBook(
                bookConverter.convertDtoToModel(bookDto)
        ));
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.PUT)
    BookDto updateBook(@PathVariable Long id,
                             @RequestBody BookDto bookDto) {
        //todo: log
        Book book = bookConverter.convertDtoToModel(bookDto);
        return bookConverter.convertModelToDto(bookService.updateBook(new Book(id,
                book.getTitle(), book.getAuthor(), book.getPrice())));
    }

    @RequestMapping(value = "/books/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteBook(@PathVariable Long id){
        //todo:log

        bookService.removeBook(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/books/{author}", method = RequestMethod.GET)
    BooksDto filterBookAuthor(@PathVariable String author) {
        //todo:Log
        return new BooksDto(bookConverter
                .convertModelsToDtos(bookService.getAllBooks()
                        .stream()
                        .filter(book -> author.equals(book.getAuthor()))
                        .collect(Collectors.toList())));
    }

    @RequestMapping(value = "/books/{minPrice}/{maxPrice}", method = RequestMethod.GET)
    BooksDto filterBookPrice(@PathVariable Double minPrice, @PathVariable Double maxPrice) {
        //todo:Log
        return new BooksDto(bookConverter
                .convertModelsToDtos(bookService.getAllBooks()
                        .stream()
                        .filter(book -> book.getPrice() >= minPrice && book.getPrice() <= maxPrice)
                        .collect(Collectors.toList())));
    }
}
