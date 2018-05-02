package com.softserve.edu.hypercinema.exception;

public class MovieNotFoundException extends ServiceException{

    public MovieNotFoundException() {
    }

    public MovieNotFoundException(String message) {
        super(message);
    }
}
