package ro.bookstore3.models.validation;


import ro.bookstore3.models.exceptions.ValidatorException;

public interface Validator<T> {
    void validate(T object) throws ValidatorException;
}
