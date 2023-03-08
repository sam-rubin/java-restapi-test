package uk.co.huntersix.spring.rest.exception;

import static uk.co.huntersix.spring.rest.Constants.PERSON_NOT_FOUND_WITH_PROVIDED_SURNAME;

public class NoMatchingPersonException extends Exception{

    private String message;

    public NoMatchingPersonException() {
        super(PERSON_NOT_FOUND_WITH_PROVIDED_SURNAME);
        this.message = PERSON_NOT_FOUND_WITH_PROVIDED_SURNAME;
    }

    public String getMessage(){
        return this.message;
    }
}
