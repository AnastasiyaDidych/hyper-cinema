package com.softserve.edu.hypercinema.exception;

public class UserAlreadyExistsException extends ConflictException {

    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
