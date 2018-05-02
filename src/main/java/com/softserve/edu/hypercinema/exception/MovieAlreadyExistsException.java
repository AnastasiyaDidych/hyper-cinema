
package com.softserve.edu.hypercinema.exception;


public class MovieAlreadyExistsException extends ServiceException {

    public MovieAlreadyExistsException(String message) {
        super(message);
    }

    public MovieAlreadyExistsException() {

    }
}
