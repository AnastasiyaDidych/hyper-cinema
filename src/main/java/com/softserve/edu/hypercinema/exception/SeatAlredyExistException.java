package com.softserve.edu.hypercinema.exception;

public class SeatAlredyExistException extends ServiceException {

    public SeatAlredyExistException() {
    }

    public SeatAlredyExistException(String message) {
        super(message);
    }

}