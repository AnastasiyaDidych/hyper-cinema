
package com.softserve.edu.hypercinema.exception;

public class MovieAlreadyExistsException extends RuntimeException{

    public MovieAlreadyExistsException(String message) {
        super(message);
    }

    public MovieAlreadyExistsException() {

    }
}
