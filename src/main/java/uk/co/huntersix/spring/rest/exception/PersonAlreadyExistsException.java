package uk.co.huntersix.spring.rest.exception;

import uk.co.huntersix.spring.rest.Constants;

public class PersonAlreadyExistsException extends Exception {

    private String message;

    public PersonAlreadyExistsException() {
        super(Constants.DUPLICATE_PERSON);
        this.message = Constants.DUPLICATE_PERSON;
    }

    public String getMessage(){
        return this.message;
    }
}
