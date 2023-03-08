package uk.co.huntersix.spring.rest.exception;


import static uk.co.huntersix.spring.rest.Constants.PERSON_NOT_FOUND;


public class PersonNotFoundException extends Exception {

    private String message;

    public PersonNotFoundException() {
        super(PERSON_NOT_FOUND);
        this.message = PERSON_NOT_FOUND;
    }

    public String getMessage(){
        return this.message;
    }
}

