package uk.co.huntersix.spring.rest.exception;

public class PersonNotAddedException extends Exception{

    private static final String PERSON_NOT_ADDED = "Error while Adding a new person";
    private String message;

    public PersonNotAddedException() {
        super(PERSON_NOT_ADDED);
        this.message = PERSON_NOT_ADDED;
    }

    public String getMessage(){
        return this.message;
    }
}
