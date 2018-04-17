package com.softserve.edu.hypercinema.exceptions;

public class SeatAlreadyExistsException extends ServiceException {

    public SeatAlreadyExistsException() {
    }

    public SeatAlreadyExistsException(String message) {
        super(message);
    }

}