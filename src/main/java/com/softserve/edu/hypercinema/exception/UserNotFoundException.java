package com.softserve.edu.hypercinema.exception;

public class UserNotFoundException extends DatabaseItemNotFoundException {

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

}
