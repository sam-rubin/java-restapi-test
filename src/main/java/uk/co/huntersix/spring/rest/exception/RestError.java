package uk.co.huntersix.spring.rest.exception;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class RestError {

    private HttpStatus status;
    private String message;

    public RestError(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public RestError(){

    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
