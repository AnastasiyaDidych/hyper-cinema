package com.softserve.edu.hypercinema.exceptions;

public class HallNotFoundException extends ServiceException {

    public HallNotFoundException() {
    }

    public HallNotFoundException(String message) {
        super(message);
    }

}