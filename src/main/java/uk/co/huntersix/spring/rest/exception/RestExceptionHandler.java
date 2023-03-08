package uk.co.huntersix.spring.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler( PersonNotFoundException.class)
    public ResponseEntity<RestError> handlePersonNotFoundError(PersonNotFoundException exception){
        return new ResponseEntity<>(new RestError(HttpStatus.NOT_FOUND,exception.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( NoMatchingPersonException.class)
    public ResponseEntity<RestError> handleNoMatchingPerson(NoMatchingPersonException exception){
        return new ResponseEntity<>(new RestError(HttpStatus.NOT_FOUND,exception.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler( PersonAlreadyExistsException.class)
    public ResponseEntity<RestError> handlePersonAlreadyExistsException(PersonAlreadyExistsException exception){
        return new ResponseEntity<>(new RestError(HttpStatus.NOT_FOUND,exception.getMessage()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler( PersonNotAddedException.class)
    public ResponseEntity<RestError> handlePersonNotAddedException(PersonNotAddedException exception){
        return new ResponseEntity<>(new RestError(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
