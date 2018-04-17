package com.softserve.edu.hypercinema.exceptions;


public class MovieAlreadyExistsException extends RuntimeException{

    public MovieAlreadyExistsException(String message) {
        super(message);
    }

    public MovieAlreadyExistsException() {

    }
}
