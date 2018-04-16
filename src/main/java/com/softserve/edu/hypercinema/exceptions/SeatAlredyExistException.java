package com.softserve.edu.hypercinema.exceptions;

public class SeatAlredyExistException extends ServiceException {

    public SeatAlredyExistException() {
    }

    public SeatAlredyExistException(String message) {
        super(message);
    }

}