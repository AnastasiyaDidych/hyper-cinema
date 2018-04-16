package com.softserve.edu.hypercinema.exception;

public class DatabaseItemNotFoundException extends ServiceException {

    public DatabaseItemNotFoundException() {
    }

    public DatabaseItemNotFoundException(String message) {
        super(message);
    }

}
