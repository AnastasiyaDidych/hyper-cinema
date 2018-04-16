package com.softserve.edu.hypercinema.exceptions;

public class SeatNotFoundException extends ServiceException {

    public SeatNotFoundException() {
    }

    public SeatNotFoundException(String message) {
        super(message);
    }

}
