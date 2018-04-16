package com.softserve.ua.exception;


public class MovieAlreadyExistsException extends RuntimeException{

    public MovieAlreadyExistsException(String message) {
        super(message);
    }

    public MovieAlreadyExistsException() {

    }
}
