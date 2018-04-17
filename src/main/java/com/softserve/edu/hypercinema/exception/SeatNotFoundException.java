package com.softserve.edu.hypercinema.exception;

public class SeatNotFoundException extends ServiceException {

    public SeatNotFoundException() {
    }

    public SeatNotFoundException(String message) {
        super(message);
    }

}
