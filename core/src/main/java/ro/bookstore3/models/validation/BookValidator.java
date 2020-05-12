package ro.bookstore3.models.validation;


import ro.bookstore3.models.Book;
import ro.bookstore3.models.exceptions.ValidatorException;

public class BookValidator implements Validator<Book> {
    @Override
    public void validate(Book object) throws ValidatorException {
        if (object.getPrice() <= 0.0)
            throw new ValidatorException("Invalid Book Price");
    }
}
