package ro.bookstore3.services.book;

import ro.bookstore3.models.Book;

import java.util.List;

public interface BookService {

    String NAME = "BookService";

    Book getBookById(Long id);

    Book addBook(Book book);

    void removeBook(Long id);

    Book updateBook(Book book);

    List<Book> getAllBooks();

    List<Book> filterBookAuthor(String author);

    List<Book> filterBookPrice(double min, double max);
}
