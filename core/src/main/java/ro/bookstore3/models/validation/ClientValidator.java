package ro.bookstore3.models.validation;


import ro.bookstore3.models.Client;
import ro.bookstore3.models.exceptions.ValidatorException;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client object) throws ValidatorException {
        if (object.getFirstName().equals(""))
            throw new ValidatorException("Invalid First Name");
        if (object.getLastName().equals(""))
            throw new ValidatorException("Invalid Last Name");
    }
}
