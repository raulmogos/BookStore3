package ro.bookstore3.services.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.bookstore3.models.Book;
import ro.bookstore3.models.validation.BookValidator;
import ro.bookstore3.repositories.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BookMainService implements BookService {

    public static final Logger log = LoggerFactory.getLogger(BookMainService.class);

    @Autowired
    private BookRepository bookRepository;
    private BookValidator bookValidator = new BookValidator();


    @Override
    public Book getBookById(Long id) {
        log.trace("getBookById - method entered: id={}", id);
        return bookRepository.getOne(id);
    }

    @Override
    public Book addBook(Book book) {
        log.trace("addBook - method entered: book={}", book);
        bookValidator.validate(book);
        log.trace("addBook - book validated: book={}", book);
        Book returnBook = bookRepository.save(book);
        log.trace("addBook - method finished");
        return returnBook;
    }

    @Override
    public void removeBook(Long id) {
        log.trace("removeBook - method entered: id={}", id);
        bookRepository.deleteById(id);
        log.trace("removeBook - method finished");
    }

    @Override
    public Book updateBook(Book newBook) {
        AtomicReference<Book> book = new AtomicReference<>();
        log.trace("updateBook - method entered: newBook={}", newBook);
        bookValidator.validate(newBook);
        log.trace("updateBook - newBook validated: newBook={}", newBook);
        bookRepository.findById(newBook.getId()).ifPresent(oldBook -> {
            oldBook.setTitle(newBook.getTitle());
            oldBook.setAuthor(newBook.getAuthor());
            oldBook.setPrice(newBook.getPrice());
            book.set(bookRepository.save(oldBook));
            log.debug("updateBook - updated: oldBook={}", oldBook);
        });
        log.trace("updateBook - method finished");
        return book.get();
    }

    @Override
    public List<Book> getAllBooks() {
        log.trace("getAllBooks - method entered");
        return bookRepository.findAll();
    }

    @Override
    public List<Book> filterBookAuthor(String author) {
        ArrayList<Book> filteredBooks = new ArrayList<>();
        bookRepository.findAll().forEach((book) -> {
            if (book.getAuthor().equals(author)) {
                filteredBooks.add(book);
            }
        });
        return filteredBooks;
    }

    @Override
    public List<Book> filterBookPrice(double min, double max) {
        ArrayList<Book> filteredBooks = new ArrayList<>();
        bookRepository.findAll().forEach((book) -> {
            if (book.getPrice() >= min && book.getPrice() <= max) {
                filteredBooks.add(book);
            }
        });
        return filteredBooks;
    }
}
