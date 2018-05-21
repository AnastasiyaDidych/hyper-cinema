package com.softserve.edu.hypercinema.exception;

public class WrongDateRentException extends ServiceException {

    public WrongDateRentException(String message) {
        super(message);
    }

    public WrongDateRentException() {
    }
}
